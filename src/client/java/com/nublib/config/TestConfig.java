package com.nublib.config;

import com.nublib.config.provider.IConfigProvider;
import com.nublib.config.serialization.BooleanSerializer;
import com.nublib.config.serialization.StringSerializer;

public class TestConfig {
	public ConfigProperty<Boolean> enabled;
	public ConfigProperty<String> someLongPropertyValue;
	public ConfigProperty<String> someExtremelyLongPropertyValueForASmallThing;
	public ConfigProperty<String> a;
	public ConfigProperty<String> b;
	public ConfigProperty<String> c;
	public ConfigProperty<String> d;
	public ConfigProperty<String> e;
	public ConfigProperty<String> f;
	public ConfigProperty<String> g;
	public ConfigProperty<String> h;
	public ConfigProperty<String> i;
	public ConfigProperty<String> j;
	public ConfigProperty<String> k;
	public ConfigProperty<String> l;
	public ConfigProperty<String> m;
	public ConfigProperty<String> n;
	public ConfigProperty<String> o;
	public ConfigProperty<String> p;
	public ConfigProperty<String> q;

	public TestConfig(IConfigProvider provider) {
		enabled = new ConfigProperty<>(provider, "enabled", true, new BooleanSerializer());
		someLongPropertyValue = new ConfigProperty<>(provider, "someLongPropertyValue", "OwO", new StringSerializer());
		a = new ConfigProperty<>(provider, "a", "UwU", new StringSerializer());
		b = new ConfigProperty<>(provider, "b", "UwU", new StringSerializer());
		c = new ConfigProperty<>(provider, "c", "UwU", new StringSerializer());
		d = new ConfigProperty<>(provider, "d", "UwU", new StringSerializer());
		e = new ConfigProperty<>(provider, "e", "UwU", new StringSerializer());
		f = new ConfigProperty<>(provider, "f", "UwU", new StringSerializer());
		g = new ConfigProperty<>(provider, "g", "UwU", new StringSerializer());
		h = new ConfigProperty<>(provider, "h", "UwU", new StringSerializer());
		i = new ConfigProperty<>(provider, "i", "UwU", new StringSerializer());
		j = new ConfigProperty<>(provider, "j", "UwU", new StringSerializer());
		k = new ConfigProperty<>(provider, "k", "UwU", new StringSerializer());
		l = new ConfigProperty<>(provider, "l", "UwU", new StringSerializer());
		m = new ConfigProperty<>(provider, "m", "UwU", new StringSerializer());
		n = new ConfigProperty<>(provider, "n", "UwU", new StringSerializer());
		o = new ConfigProperty<>(provider, "o", "UwU", new StringSerializer());
		p = new ConfigProperty<>(provider, "p", "UwU", new StringSerializer());
		q = new ConfigProperty<>(provider, "q", "UwU", new StringSerializer());
	}
}
