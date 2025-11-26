package frc.robot;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.CANdleConfiguration;
import com.ctre.phoenix6.controls.ColorFlowAnimation;
import com.ctre.phoenix6.controls.EmptyAnimation;
import com.ctre.phoenix6.controls.FireAnimation;
import com.ctre.phoenix6.controls.LarsonAnimation;
import com.ctre.phoenix6.controls.RainbowAnimation;
import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;
import com.ctre.phoenix6.signals.StatusLedWhenActiveValue;
import com.ctre.phoenix6.signals.StripTypeValue;

import edu.wpi.first.wpilibj.util.Color;

enum colors{
    kGreen,
    kWhite,
    kRed,
    kViolet
}


public class LED {

  private static final RGBWColor kGreen = new RGBWColor(0, 217, 0, 0);
  private static final RGBWColor kWhite = new RGBWColor(Color.kWhite).scaleBrightness(0.5);
  private static final RGBWColor kViolet = RGBWColor.fromHSV(Degrees.of(270), 0.9, 0.8);
  private static final RGBWColor kRed = RGBWColor.fromHex("#D9000000").orElseThrow();

  private static final int kSlot0StartIdx = 0;
  private static final int kSlot0EndIdx = 7;

  private final CANdle m_candle = new CANdle(1, "rio");
  

    public LED(){
     /* Configure CANdle */
     CANdleConfiguration cfg = new CANdleConfiguration();
     /* set the LED strip type and brightness */
     cfg.LED.StripType = StripTypeValue.GRB;
     cfg.LED.BrightnessScalar = 0.5;
    }

    public void setColor(int firstLED, int lastLED, colors color){
        m_candle.setControl(new SolidColor(firstLED, lastLED).withColor(kGreen));
    }
}
