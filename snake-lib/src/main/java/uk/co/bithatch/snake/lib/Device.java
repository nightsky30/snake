package uk.co.bithatch.snake.lib;

import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.bithatch.snake.lib.effects.Effect;

public interface Device extends AutoCloseable, Grouping, Lit {

	public interface Listener {
		void changed(Device device, Region region);
	}

	void addListener(Listener listener);

	void addMacro(MacroSequence macro);

	void deleteMacro(Key key);

	String exportMacros();

	int getBattery();

	default short getBrightness() {
		double tot = 0;
		int of = 0;
		for (Region r : getRegions()) {
			if (r.getCapabilities().contains(Capability.BRIGHTNESS_PER_REGION)) {
				tot += r.getBrightness();
				of++;
			}
		}
		return (short) (tot / (double) of);
	}

	Set<Capability> getCapabilities();

	int[] getDPI();

	String getDriverVersion();

	Effect getEffect();

	String getFirmware();

	int getIdleTime();

	String getImage();

	String getImageUrl(BrandingImage image);

	String getKeyboardLayout();

	byte getLowBatteryThreshold();

	Map<Key, MacroSequence> getMacros();

	int[] getMatrixSize();

	int getMaxDPI();

	String getMode();

	String getName();

	int getPollRate();

	List<Region> getRegions();

	String getSerial();

	DeviceType getType();

	void importMacros(String macros);

	boolean isCharging();

	boolean isGameMode();

	boolean isModeModifier();

	boolean isSuspended();

	void removeListener(Listener listener);

	void setBrightness(short brightness);

	void setDPI(short x, short y);

	void setEffect(Effect effect);

	void setGameMode(boolean gameMode);

	void setIdleTime(int idleTime);

	void setLowBatteryThreshold(byte threshold);

	void setModeModifier(boolean modify);

	void setPollRate(int pollRate);

	void setSuspended(boolean suspended);
}
