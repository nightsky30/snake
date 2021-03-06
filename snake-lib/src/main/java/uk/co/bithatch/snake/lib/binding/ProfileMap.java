package uk.co.bithatch.snake.lib.binding;

import java.util.Map;

import uk.co.bithatch.linuxio.EventCode;
import uk.co.bithatch.linuxio.EventCode.Type;
import uk.co.bithatch.snake.lib.effects.Matrix;

public interface ProfileMap {

	void setMatrix(Matrix matrix);

	Matrix getMatrix();

	boolean[] getLEDs();

	void setLEDs(boolean red, boolean green, boolean blue);

	default void setLEDs(boolean[] rgb) {
		setLEDs(rgb[0], rgb[1], rgb[2]);
	}

	void record(int keyCode);

	boolean isActive();

	void activate();

	Profile getProfile();

	String getId();

	Map<EventCode, MapSequence> getSequences();

	void remove();

	void stopRecording();

	boolean isRecording();

	int getRecordingMacroKey();

	boolean isDefault();

	void makeDefault();

	MapSequence addSequence(EventCode key, boolean addDefault);

	@SuppressWarnings("resource")
	default EventCode getNextFreeKey() {
		for (EventCode code : EventCode.filteredForType(getProfile().getDevice().getSupportedInputEvents(), Type.EV_KEY)) {
			if (!getSequences().containsKey(code))
				return code;
		}
		throw new IllegalStateException("No free supported input codes. Please remove an existing mapping.");
	}


}
