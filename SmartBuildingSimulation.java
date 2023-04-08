import java.util.Timer;
import java.util.TimerTask;

public class SmartBuildingSimulation {
    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        Lights lights = new Lights();
        TemperatureControl tempControl = new TemperatureControl();
        MotionDetector motionDetector = new MotionDetector();
        MusicPlayer musicPlayer = new MusicPlayer();
        
        // Schedule updates for each system at a fixed interval
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                double temperature = tempControl.getTemperature();
                double humidity = tempControl.getHumidity();
                tempControl.update(temperature, humidity);
                
                boolean motionDetected = motionDetector.isMotionDetected();
                lights.update(motionDetected);
                
                boolean alarmTriggered = alarm.isAlarmTriggered();
                if (alarmTriggered) {
                    musicPlayer.playAlarmSound();
                } else {
                    musicPlayer.stopAlarmSound();
                }
            }
        }, 0, 1000); // Update every second
    }
}

class Alarm {
    private boolean alarmTriggered;
    
    public boolean isAlarmTriggered() {
        return alarmTriggered;
    }
    
    public void triggerAlarm() {
        alarmTriggered = true;
    }
    
    public void resetAlarm() {
        alarmTriggered = false;
    }
}

class Lights {
    private boolean lightsOn;
    
    public boolean areLightsOn() {
        return lightsOn;
    }
    
    public void turnLightsOn() {
        lightsOn = true;
    }
    
    public void turnLightsOff() {
        lightsOn = false;
    }
    
    public void update(boolean motionDetected) {
        if (motionDetected) {
            turnLightsOn();
        } else {
            turnLightsOff();
        }
    }
}

class TemperatureControl {
    private double temperature;
    private double humidity;
    
    public double getTemperature() {
        // Simulate temperature reading from a sensor
        return temperature + Math.random() * 2 - 1; // Add some noise to the reading
    }
    
    public double getHumidity() {
        // Simulate humidity reading from a sensor
        return humidity + Math.random() * 2 - 1; // Add some noise to the reading
    }
    
    public void update(double temperature, double humidity) {
        // Implement control logic here to adjust the HVAC system based on the current temperature and humidity
        this.temperature = temperature;
        this.humidity = humidity;
    }
}

class MotionDetector {
    public boolean isMotionDetected() {
        // Simulate motion detection reading from a sensor
        return Math.random() < 0.2; // 20% chance of motion being detected
    }
}

class MusicPlayer {
    private boolean playingAlarmSound;
    
    public void playAlarmSound() {
        if (!playingAlarmSound) {
            // Implement code here to play alarm sound
            playingAlarmSound = true;
        }
    }
    
    public void stopAlarmSound() {
        if (playingAlarmSound) {
            // Implement code here to stop alarm sound
            playingAlarmSound = false;
        }
    }
}
