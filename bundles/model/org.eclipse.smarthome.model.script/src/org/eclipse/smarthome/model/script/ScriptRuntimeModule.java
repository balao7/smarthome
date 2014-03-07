/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * generated by Xtext
 */
package org.eclipse.smarthome.model.script;

import org.eclipse.smarthome.core.scriptengine.Script;
import org.eclipse.smarthome.core.scriptengine.ScriptEngine;
import org.eclipse.smarthome.model.script.internal.engine.ScriptEngineImpl;
import org.eclipse.smarthome.model.script.internal.engine.ScriptImpl;
import org.eclipse.smarthome.model.script.interpreter.ScriptInterpreter;
import org.eclipse.smarthome.model.script.scoping.ActionClassLoader;
import org.eclipse.smarthome.model.script.scoping.ActionClasspathBasedTypeScopeProvider;
import org.eclipse.smarthome.model.script.scoping.ActionClasspathTypeProviderFactory;
import org.eclipse.smarthome.model.script.scoping.ScriptImplicitlyImportedTypes;
import org.eclipse.smarthome.model.script.scoping.ScriptImportSectionNamespaceScopeProvider;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.IGenerator.NullGenerator;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.interpreter.IExpressionInterpreter;
import org.eclipse.xtext.xbase.scoping.batch.ImplicitlyImportedTypes;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("restriction")
public class ScriptRuntimeModule extends org.eclipse.smarthome.model.script.AbstractScriptRuntimeModule {


	public Class<? extends ImplicitlyImportedTypes> bindImplicitlyImportedTypes() {
		return ScriptImplicitlyImportedTypes.class;
	}
	
	public Class<? extends Script> bindScript() {
		return ScriptImpl.class;
	}
	
	public Class<? extends IExpressionInterpreter> bindIExpressionInterpreter() {
		return ScriptInterpreter.class;
	}
	
	/* we need this so that our pluggable actions can be resolved at design time */
	@Override
	public Class<? extends org.eclipse.xtext.common.types.access.IJvmTypeProvider.Factory> bindIJvmTypeProvider$Factory() {
		return ActionClasspathTypeProviderFactory.class;
	}
	
	/* we need this so that our pluggable actions can be resolved when being parsed at runtime */
	@Override
	public Class<? extends org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider> bindAbstractTypeScopeProvider() {
		return ActionClasspathBasedTypeScopeProvider.class;
	}

	/* we need this so that our pluggable actions can be resolved when being executed at runtime */
	@Override
	public ClassLoader bindClassLoaderToInstance() {
		return new ActionClassLoader(getClass().getClassLoader());
	}
	
	@Override
	public Class<? extends IGenerator> bindIGenerator() {
		return NullGenerator.class;
	}
	
	public Class<? extends ScriptEngine> bindScriptEngine() {
		return ScriptEngineImpl.class;
	}
	
	public void configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(ScriptImportSectionNamespaceScopeProvider.class);
	}
	
}
