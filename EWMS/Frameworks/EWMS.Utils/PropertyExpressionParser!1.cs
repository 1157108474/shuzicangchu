namespace EWMS.Utils
{
    using System;
    using System.Linq.Expressions;
    using System.Reflection;

    public class PropertyExpressionParser<T>
    {
        private readonly object _item;
        private PropertyInfo _property;

        public PropertyExpressionParser(object item, Expression<Func<T, object>> propertyExpression)
        {
            this._item = item;
            this._property = PropertyExpressionParser<T>.GetProperty(propertyExpression);
        }

        private static PropertyInfo GetProperty(Expression<Func<T, object>> exp)
        {
            PropertyInfo member;
            if (exp.Body.NodeType == ExpressionType.Convert)
            {
                member = ((MemberExpression) ((UnaryExpression) exp.Body).Operand).Member as PropertyInfo;
            }
            else
            {
                member = ((MemberExpression) exp.Body).Member as PropertyInfo;
            }
            if (member == null)
            {
                throw new ArgumentException(string.Format("Expression '{0}' does not refer to a property.", exp.ToString()));
            }
            return typeof(T).GetProperty(member.Name);
        }

        public string Name
        {
            get
            {
                return this._property.Name;
            }
        }

        public System.Type Type
        {
            get
            {
                return ReflectionUtil.GetPropertyType(this._property);
            }
        }

        public object Value
        {
            get
            {
                return ReflectionUtil.GetPropertyValue(this._item, this._property);
            }
        }
    }
}

