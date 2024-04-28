package com.nublib.config.option;

import com.nublib.config.screen.model.section.control.Control;

public interface IHasControl<T> {
	Control<T> getControl();
}
