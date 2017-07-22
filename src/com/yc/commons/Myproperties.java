package com.yc.commons;


	

	import java.io.IOException;
	import java.io.InputStream;
	import java.util.Properties;
	//继承Proproties,这样就自定义的Myproperties拥有Properties中的资源
	//设为单例模式
	public class Myproperties extends Properties {
		private static Myproperties myProperties;
		
		private Myproperties() throws IOException{
			InputStream in=Myproperties.class.getClassLoader().getResourceAsStream("db.properties");
			this.load(in);
		}
		
		public static Myproperties getInstance() throws IOException{
			if(null==myProperties){
				return new Myproperties();
			}
			return myProperties;
		}
	}


