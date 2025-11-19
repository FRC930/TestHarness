package frc.robot;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.configs.CANdleConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.EmptyAnimation;
import com.ctre.phoenix6.controls.RainbowAnimation;
import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;
import com.ctre.phoenix6.signals.StripTypeValue;

import edu.wpi.first.wpilibj.util.Color;

enum colors{
    Green,
    White,
    Violet,
    Red
}
public class LED {
    private final CANdle m_candle = new CANdle(1, "rio");

    /* color can be constructed from RGBW, a WPILib Color/Color8Bit, HSV, or hex */
    private static final RGBWColor kGreen = new RGBWColor(0, 217, 0, 0);
    private static final RGBWColor kWhite = new RGBWColor(Color.kWhite).scaleBrightness(0.5);
    private static final RGBWColor kViolet = RGBWColor.fromHSV(Degrees.of(270), 0.9, 0.8);
    private static final RGBWColor kRed = RGBWColor.fromHex("#D9000000").orElseThrow();
    /*
    * Start and end index for LED animations.
    * 0-7 are onboard, 8-399 are an external strip.
    * CANdle supports 8 animation slots (0-7).
    */
    private static final int kSlot0StartIdx = 0;
    private static final int kSlot0EndIdx = 7;

    public LED(){
        /* Configure CANdle */
        CANdleConfiguration cfg = new CANdleConfiguration();
        /* set the LED strip type and brightness */
        cfg.LED.StripType = StripTypeValue.GRB;
        cfg.LED.BrightnessScalar = 0.5;
        /* disable status LED when being controlled */
        //cfg.CANdleFeatures.StatusLedWhenActive = StatusLedWhenActiveValue.Disabled;
        m_candle.getConfigurator().apply(cfg);
        /* clear all previous animations */
        turnOffLED();
    }

    public void setColor(int startIndex, int endIndex, colors color) {
        turnOffLED();
        switch (color) {
            case Green:
                m_candle.setControl(new SolidColor(startIndex, endIndex).withColor(kGreen));
                break;
            case White:
                m_candle.setControl(new SolidColor(startIndex, endIndex).withColor(kWhite));
                break;
            case Red:
                m_candle.setControl(new SolidColor(startIndex, endIndex).withColor(kRed));
                break;
            case Violet:
                m_candle.setControl(new SolidColor(startIndex, endIndex).withColor(kViolet));
                break;
            default:
                m_candle.setControl(new SolidColor(startIndex, endIndex).withColor(kWhite));
                break;
        }
        // m_candle.setControl(new SolidColor(startIndex, endIndex).withColor(color));
    }

    public void setAnimation(ControlRequest animation) {
        turnOffLED();
        m_candle.setControl(animation);
    }

    public void turnOffLED() {
        /* clear all previous animations */
        for (int i = 0; i < 8; ++i) {
            m_candle.setControl(new EmptyAnimation(i));
        }
    }

}
