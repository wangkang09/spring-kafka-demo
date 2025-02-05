package com.wangkang.springkafkademo.po;

/**
 * @author Gary Russell
 * @since 2.2.1
 *
 */
public class Foo {

	private String foo;

	public Foo() {
	}

	public Foo(String foo) {
		this.foo = foo;
	}

	public String getFoo() {
		return this.foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	@Override
	public String toString() {
		return "Foo1 [foo=" + this.foo + "]";
	}

}
