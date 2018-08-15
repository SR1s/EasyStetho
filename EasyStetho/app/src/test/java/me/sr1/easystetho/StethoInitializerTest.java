package me.sr1.easystetho;

import android.content.Context;

import com.facebook.stetho.Stetho;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * 初始化测试
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(Stetho.class)
public class StethoInitializerTest {

	@Rule
	public PowerMockRule rule = new PowerMockRule();

	@Before
	public void setup() throws Exception {
		PowerMockito.mockStatic(Stetho.class);
		doNothing().when(Stetho.class, "initializeWithDefaults", any(Context.class));
	}

	@Test
	public void testStethoInitializer() throws Exception {
		Robolectric.buildContentProvider(StethoInitializer.class).create().get();
		verifyStatic(times(1));
		Stetho.initializeWithDefaults(any(Context.class));
	}

}
