package frc.robot;

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

public class LED {
    /* Configure CANdle */
        CANdleConfiguration cfg = new CANdleConfiguration();
        /* set the LED strip type and brightness */
        cfg.LED.StripType = StripTypeValue.GRB;
        cfg.LED.BrightnessScalar = 0.5;
        /* disable status LED when being controlled */
        //cfg.CANdleFeatures.StatusLedWhenActive = StatusLedWhenActiveValue.Disabled;
        m_candle.getConfigurator().apply(cfg);
        /* clear all previous animations */
        for (int i = 0; i < 8; ++i) {
            m_candle.setControl(new EmptyAnimation(i));
        }
        /* set the onboard LEDs to a solid color */
        m_candle.setControl(new SolidColor(0, 3).withColor(kGreen));
        m_candle.setControl(new SolidColor(4, 7).withColor(kWhite));
        m_candle.setControl(
          new RainbowAnimation(kSlot0StartIdx, kSlot0EndIdx).withSlot(0));






}
