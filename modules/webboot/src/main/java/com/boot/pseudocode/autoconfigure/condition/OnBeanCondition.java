package com.boot.pseudocode.autoconfigure.condition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Order(Ordered.LOWEST_PRECEDENCE)
class OnBeanCondition extends SpringBootCondition implements ConfigurationCondition {

    public static final String FACTORY_BEAN_OBJECT_TYPE = BeanTypeRegistry.FACTORY_BEAN_OBJECT_TYPE;

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,AnnotatedTypeMetadata metadata) {
        ConditionMessage matchMessage = ConditionMessage.empty();
        if (metadata.isAnnotated(ConditionalOnBean.class.getName())) {
            OnBeanCondition.BeanSearchSpec spec = new OnBeanCondition.BeanSearchSpec(context, metadata, ConditionalOnBean.class);
            List<String> matching = getMatchingBeans(context, spec);
            if (matching.isEmpty()) {
                return ConditionOutcome.noMatch(ConditionMessage.forCondition(ConditionalOnBean.class, spec).didNotFind("any beans").atAll());
            }
            matchMessage = matchMessage.andCondition(ConditionalOnBean.class, spec).found("bean", "beans")
                .items(ConditionMessage.Style.QUOTE, matching);
        }
        if (metadata.isAnnotated(ConditionalOnSingleCandidate.class.getName())) {
            OnBeanCondition.BeanSearchSpec spec = new OnBeanCondition.SingleCandidateBeanSearchSpec(context, metadata,ConditionalOnSingleCandidate.class);
            List<String> matching = getMatchingBeans(context, spec);
            if (matching.isEmpty()) {
                return ConditionOutcome.noMatch(ConditionMessage.forCondition(ConditionalOnSingleCandidate.class, spec).didNotFind("any beans").atAll());
            }
            else if (!hasSingleAutowireCandidate(context.getBeanFactory(), matching, spec.getStrategy() == SearchStrategy.ALL)) {
                return ConditionOutcome.noMatch(ConditionMessage.forCondition(ConditionalOnSingleCandidate.class, spec)
                        .didNotFind("a primary bean from beans").items(ConditionMessage.Style.QUOTE, matching));
            }
            matchMessage = matchMessage.andCondition(ConditionalOnSingleCandidate.class, spec)
                    .found("a primary bean from beans").items(ConditionMessage.Style.QUOTE, matching);
        }
        if (metadata.isAnnotated(ConditionalOnMissingBean.class.getName())) {
            OnBeanCondition.BeanSearchSpec spec = new OnBeanCondition.BeanSearchSpec(context, metadata, ConditionalOnMissingBean.class);
            List<String> matching = getMatchingBeans(context, spec);
            if (!matching.isEmpty()) {
                return ConditionOutcome.noMatch(ConditionMessage.forCondition(ConditionalOnMissingBean.class, spec)
                    .found("bean", "beans").items(ConditionMessage.Style.QUOTE, matching));
            }
            matchMessage = matchMessage.andCondition(ConditionalOnMissingBean.class, spec).didNotFind("any beans").atAll();
        }
        return ConditionOutcome.match(matchMessage);
    }

    private List<String> getMatchingBeans(ConditionContext context, OnBeanCondition.BeanSearchSpec beans) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        if (beans.getStrategy() == SearchStrategy.PARENTS) {
            BeanFactory parent = beanFactory.getParentBeanFactory();
            beanFactory = (ConfigurableListableBeanFactory) parent;
        }
        if (beanFactory == null) {
            return Collections.emptyList();
        }
        List<String> beanNames = new ArrayList<String>();
        boolean considerHierarchy = beans.getStrategy() != SearchStrategy.CURRENT;
        for (String type : beans.getTypes()) {
            beanNames.addAll(getBeanNamesForType(beanFactory, type, context.getClassLoader(), considerHierarchy));
        }
        for (String ignoredType : beans.getIgnoredTypes()) {
            beanNames.removeAll(getBeanNamesForType(beanFactory, ignoredType, context.getClassLoader(), considerHierarchy));
        }
        for (String annotation : beans.getAnnotations()) {
            beanNames.addAll(Arrays.asList(getBeanNamesForAnnotation(beanFactory, annotation, context.getClassLoader(), considerHierarchy)));
        }
        for (String beanName : beans.getNames()) {
            if (containsBean(beanFactory, beanName, considerHierarchy)) {
                beanNames.add(beanName);
            }
        }
        return beanNames;
    }

    private boolean containsBean(ConfigurableListableBeanFactory beanFactory, String beanName, boolean considerHierarchy) {
        if (considerHierarchy) {
            return beanFactory.containsBean(beanName);
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private Collection<String> getBeanNamesForType(ListableBeanFactory beanFactory,
               String type, ClassLoader classLoader, boolean considerHierarchy) throws LinkageError {
        try {
            Set<String> result = new LinkedHashSet<String>();
            collectBeanNamesForType(result, beanFactory, ClassUtils.forName(type, classLoader), considerHierarchy);
            return result;
        }
        catch (ClassNotFoundException ex) {
            return Collections.emptySet();
        }
        catch (NoClassDefFoundError ex) {
            return Collections.emptySet();
        }
    }

    private void collectBeanNamesForType(Set<String> result, ListableBeanFactory beanFactory, Class<?> type, boolean considerHierarchy) {
        result.addAll(BeanTypeRegistry.get(beanFactory).getNamesForType(type));
        if (considerHierarchy && beanFactory instanceof HierarchicalBeanFactory) {
            BeanFactory parent = ((HierarchicalBeanFactory) beanFactory).getParentBeanFactory();
            if (parent instanceof ListableBeanFactory) {
                collectBeanNamesForType(result, (ListableBeanFactory) parent, type, considerHierarchy);
            }
        }
    }

    private String[] getBeanNamesForAnnotation(ConfigurableListableBeanFactory beanFactory, String type,
            ClassLoader classLoader, boolean considerHierarchy) throws LinkageError {
        Set<String> names = new HashSet<String>();
        try {
            Class<? extends Annotation> annotationType = (Class<? extends Annotation>) ClassUtils.forName(type, classLoader);
            collectBeanNamesForAnnotation(names, beanFactory, annotationType, considerHierarchy);
        }
        catch (ClassNotFoundException e) {
        }
        return StringUtils.toStringArray(names);
    }

    private void collectBeanNamesForAnnotation(Set<String> names,ListableBeanFactory beanFactory,
           Class<? extends Annotation> annotationType, boolean considerHierarchy) {
        names.addAll(BeanTypeRegistry.get(beanFactory).getNamesForAnnotation(annotationType));
        if (considerHierarchy) {
            BeanFactory parent = ((HierarchicalBeanFactory) beanFactory).getParentBeanFactory();
            if (parent instanceof ListableBeanFactory) {
                collectBeanNamesForAnnotation(names, (ListableBeanFactory) parent, annotationType, considerHierarchy);
            }
        }
    }

    private boolean hasSingleAutowireCandidate(ConfigurableListableBeanFactory beanFactory, List<String> beanNames, boolean considerHierarchy) {
        return (beanNames.size() == 1 || getPrimaryBeans(beanFactory, beanNames, considerHierarchy).size() == 1);
    }

    private List<String> getPrimaryBeans(ConfigurableListableBeanFactory beanFactory, List<String> beanNames, boolean considerHierarchy) {
        List<String> primaryBeans = new ArrayList<String>();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = findBeanDefinition(beanFactory, beanName, considerHierarchy);
            if (beanDefinition != null && beanDefinition.isPrimary()) {
                primaryBeans.add(beanName);
            }
        }
        return primaryBeans;
    }

    private BeanDefinition findBeanDefinition(ConfigurableListableBeanFactory beanFactory, String beanName, boolean considerHierarchy) {
        if (beanFactory.containsBeanDefinition(beanName)) {
            return beanFactory.getBeanDefinition(beanName);
        }
        if (considerHierarchy && beanFactory.getParentBeanFactory() instanceof ConfigurableListableBeanFactory) {
            return findBeanDefinition(((ConfigurableListableBeanFactory) beanFactory.getParentBeanFactory()), beanName, considerHierarchy);
        }
        return null;
    }

    private static class BeanSearchSpec {

        private final Class<?> annotationType;

        private final List<String> names = new ArrayList<String>();

        private final List<String> types = new ArrayList<String>();

        private final List<String> annotations = new ArrayList<String>();

        private final List<String> ignoredTypes = new ArrayList<String>();

        private final SearchStrategy strategy;

        BeanSearchSpec(ConditionContext context, AnnotatedTypeMetadata metadata, Class<?> annotationType) {
            this.annotationType = annotationType;
            MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(annotationType.getName(), true);
            collect(attributes, "name", this.names);
            collect(attributes, "value", this.types);
            collect(attributes, "type", this.types);
            collect(attributes, "annotation", this.annotations);
            collect(attributes, "ignored", this.ignoredTypes);
            collect(attributes, "ignoredType", this.ignoredTypes);
            this.strategy = (SearchStrategy) metadata.getAnnotationAttributes(annotationType.getName()).get("search");
            OnBeanCondition.BeanTypeDeductionException deductionException = null;
            try {
                if (this.types.isEmpty() && this.names.isEmpty()) {
                    addDeducedBeanType(context, metadata, this.types);
                }
            } catch (OnBeanCondition.BeanTypeDeductionException ex) {
                deductionException = ex;
            }
            validate(deductionException);
        }

        protected void validate(OnBeanCondition.BeanTypeDeductionException ex) {
            if (!hasAtLeastOne(this.types, this.names, this.annotations)) {
                String message = annotationName() + " did not specify a bean using type, name or annotation";
                if (ex == null) {
                    throw new IllegalStateException(message);
                }
                throw new IllegalStateException(message + " and the attempt to deduce the bean's type failed", ex);
            }
        }

        private boolean hasAtLeastOne(List<?>... lists) {
            for (List<?> list : lists) {
                if (!list.isEmpty()) {
                    return true;
                }
            }
            return false;
        }

        protected String annotationName() {
            return "@" + ClassUtils.getShortName(this.annotationType);
        }

        protected void collect(MultiValueMap<String, Object> attributes, String key, List<String> destination) {
            List<?> values = attributes.get(key);
            if (values != null) {
                for (Object value : values) {
                    if (value instanceof String[]) {
                        Collections.addAll(destination, (String[]) value);
                    }
                    else {
                        destination.add((String) value);
                    }
                }
            }
        }

        private void addDeducedBeanType(ConditionContext context, AnnotatedTypeMetadata metadata, final List<String> beanTypes) {
            if (metadata instanceof MethodMetadata && metadata.isAnnotated(Bean.class.getName())) {
                addDeducedBeanTypeForBeanMethod(context, metadata, beanTypes, (MethodMetadata) metadata);
            }
        }

        private void addDeducedBeanTypeForBeanMethod(ConditionContext context, AnnotatedTypeMetadata metadata,
            final List<String> beanTypes,final MethodMetadata methodMetadata) {
            try {
                Class<?> configClass = ClassUtils.forName(methodMetadata.getDeclaringClassName(), context.getClassLoader());
                ReflectionUtils.doWithMethods(configClass, new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method)
                            throws IllegalArgumentException, IllegalAccessException {
                        if (methodMetadata.getMethodName().equals(method.getName())) {
                            beanTypes.add(method.getReturnType().getName());
                        }
                    }
                });
            } catch (Throwable ex) {
                throw new OnBeanCondition.BeanTypeDeductionException(methodMetadata.getDeclaringClassName(), methodMetadata.getMethodName(), ex);
            }
        }

        public SearchStrategy getStrategy() {
            return (this.strategy != null ? this.strategy : SearchStrategy.ALL);
        }

        public List<String> getNames() {
            return this.names;
        }

        public List<String> getTypes() {
            return this.types;
        }

        public List<String> getAnnotations() {
            return this.annotations;
        }

        public List<String> getIgnoredTypes() {
            return this.ignoredTypes;
        }
    }

    private static class SingleCandidateBeanSearchSpec extends OnBeanCondition.BeanSearchSpec {
        SingleCandidateBeanSearchSpec(ConditionContext context, AnnotatedTypeMetadata metadata, Class<?> annotationType) {
            super(context, metadata, annotationType);
        }

        @Override
        protected void collect(MultiValueMap<String, Object> attributes, String key,List<String> destination) {
            super.collect(attributes, key, destination);
            destination.removeAll(Arrays.asList("", Object.class.getName()));
        }

        @Override
        protected void validate(OnBeanCondition.BeanTypeDeductionException ex) {
            Assert.isTrue(getTypes().size() == 1, annotationName()
                    + " annotations must specify only one type (got " + getTypes() + ")");
        }

    }

    static final class BeanTypeDeductionException extends RuntimeException {
        private BeanTypeDeductionException(String className, String beanMethodName, Throwable cause) {
            super("Failed to deduce bean type for " + className + "." + beanMethodName, cause);
        }
    }
}
