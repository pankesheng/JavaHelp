package com;

import java.util.Date;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

public class HelloWorldPlushlet {
	static public class HwPlushlet extends EventPullSource {
		// –›√ﬂŒÂ√Î
		@Override
		protected long getSleepTime() {
			System.out.println(".....................getSleepTime() begin...........................");
			return 5000;
		}

		@Override
		protected Event pullEvent() {
			System.out.println("......................pullEvent() begin...........................");
			Event event = Event.createDataEvent("/cuige/he");
			event.setField("mess", (new Date()).toString());
			return event;
		}
	}
}
