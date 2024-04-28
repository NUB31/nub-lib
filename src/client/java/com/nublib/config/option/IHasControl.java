package com.nublib.config.option;

import com.nublib.config.screen.page.section.control.Control;

public interface IHasControl<T> {
	Control<T> getControl();
}
