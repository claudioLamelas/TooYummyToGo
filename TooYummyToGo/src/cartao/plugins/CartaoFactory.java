package cartao.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import cartao.plugins.CartaoPlugin;

public class CartaoFactory {

	public static CartaoPlugin criaCartao() {
		Properties pluginsProp = new Properties();
		
		try {
			pluginsProp.load(new FileInputStream(new File("plugin.properties")));
			
			String activatedPlugins = pluginsProp.getProperty("cartaoAUsar");
			try {
				@SuppressWarnings("unchecked")
				Class<CartaoPlugin> klass = (Class<CartaoPlugin>) Class.forName(activatedPlugins);
				Constructor<CartaoPlugin> cons = klass.getConstructor();
				CartaoPlugin c = cons.newInstance();
				return c;
				
			} catch (ClassNotFoundException e) {
				// Do nothing, just ignore
			} catch (NoSuchMethodException e) {
				// Do nothing, just ignore
			} catch (SecurityException e) {
				// Do nothing, just ignore
			} catch (InstantiationException e) {
				// Do nothing, just ignore
			} catch (IllegalAccessException e) {
				// Do nothing, just ignore
			} catch (IllegalArgumentException e) {
				// Do nothing, just ignore
			} catch (InvocationTargetException e) {
				// Do nothing, just ignore
			}		
		} catch (FileNotFoundException e) {
			System.out.println("Maria");
		} catch (IOException e) {
			// Do nothing, just ignore
		}	
			return null;
	}

}
