package kr.maxerve.admin.framework;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class CustomPropertiesFactoryBean extends PropertiesLoaderSupport
implements FactoryBean<Properties>, InitializingBean {
	private boolean singleton = true;

	private Properties singletonInstance;
	
	// The prefix and suffix for constant names
   // within property values
   private static final String START_CONST = "${";
   private static final String END_CONST = "}";


   // The maximum depth for recursive substitution
   // of constants within property values
   // (e.g., A={B} .. B={C} .. C={D} .. etc.)
   private static final int MAX_SUBST_DEPTH = 5;


	/**
	 * Set whether a shared 'singleton' Properties instance should be
	 * created, or rather a new Properties instance on each request.
	 * <p>Default is "true" (a shared singleton).
	 */
	public final void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public final boolean isSingleton() {
		return this.singleton;
	}


	public final void afterPropertiesSet() throws IOException {
		if (this.singleton) {
			this.singletonInstance = createProperties();
		}
	}

	public final Properties getObject() throws IOException {
		if (this.singleton) {
			return this.singletonInstance;
		}
		else {
			return createProperties();
		}
	}

	public Class<Properties> getObjectType() {
		return Properties.class;
	}


	/**
	 * Template method that subclasses may override to construct the object
	 * returned by this factory. The default implementation returns the
	 * plain merged Properties instance.
	 * <p>Invoked on initialization of this FactoryBean in case of a
	 * shared singleton; else, on each {@link #getObject()} call.
	 * @return the object returned by this factory
	 * @throws IOException if an exception occured during properties loading
	 * @see #mergeProperties()
	 */
	protected Properties createProperties() throws IOException {
		return (Properties) createInstance();
	}

	/**
	 * Template method that subclasses may override to construct the object
	 * returned by this factory. The default implementation returns the
	 * plain merged Properties instance.
	 * <p>Invoked on initialization of this FactoryBean in case of a
	 * shared singleton; else, on each {@link #getObject()} call.
	 * @return the object returned by this factory
	 * @throws IOException if an exception occured during properties loading
	 * @deprecated as of Spring 3.0, in favor of {@link #createProperties()}
	 */
	@Deprecated
	protected Object createInstance() throws IOException {
		Properties properties = mergeProperties();
		
		for (String key : properties.keySet().toArray(new String[0])) {
			parse(properties, key);
		}
		
		return properties;
	}
	
	/**
	 * Searches for the property with the specified
	 * key in this property list. If the key is not
	 * found in this property list, the default
	 * property list, and its defaults, recursively,
	 * are then checked. The method returns
	 * <code>null</code> if the property is not found.
	 *
	 * @param   key   the property key.
	 * @return  the value in this property list with
	 *    the specified key value.
	*/
	private void parse(Properties prop, String key) {


	   // Return the property value starting at level 0
	   prop.setProperty(key, parse(prop, key, 0));
	}


	/**
	 * Searches for the property with the specified
	 * key in this property list. If the key is not
	 * found in this property list, the default
	 * property list, and its defaults, recursively,
	 * are then checked. The method returns
	 * <code>null</code> if the property is not found.
	 *
	 * <p>The level parameter specifies the current
	 * level of recursive constant substitution. If
	 * the requested property value includes a
	 * constant, its value is substituted in place
	 * through a recursive call to this method,
	 * incrementing the level. Once level exceeds
	 * MAX_SUBST_DEPTH, no further constant
	 * substitutions are performed within the
	 * current requested value.
	 *
	 * @param   key   the property key.
	 * @param level  the level of recursion so far
	 * @return  the value in this property list with
	 * the specified key value.
	 */
	private String parse(Properties prop, String key, int level) {

		String value = prop.getProperty(key);
	   if (value != null) {


	      // Get the index of the first constant, if any
	      int beginIndex = 0;
	      int startName = value.indexOf(START_CONST, beginIndex);


	      while (startName != -1) {
	         if (level+1 > MAX_SUBST_DEPTH) {
	            // Exceeded MAX_SUBST_DEPTH
	            // Return the value as is
	            return value;
	         }


	         int endName = value.indexOf(END_CONST, startName);
	         if (endName == -1) {
	            // Terminating symbol not found
	            // Return the value as is
	            return value;
	         }


	         String constName = value.substring(startName+START_CONST.length(), endName);
	         String constValue = parse(prop, constName, level+1);


	         if (constValue == null) {
	            // Property name not found
	            // Return the value as is
	            return value;
	         }


	         // Insert the constant value into the
	         // original property value
	         String newValue = (startName>0)
	            ? value.substring(0, startName) : "";
	         newValue += constValue;


	         // Start checking for constants at this index
	         beginIndex = newValue.length();


	         // Append the remainder of the value
	         newValue += value.substring(endName+1);


	         value = newValue;


	         // Look for the next constant
	         startName = value.indexOf(START_CONST, beginIndex);
	      }
	   }


	   // Return the value as is
	   return value;
	}
}
