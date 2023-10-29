package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MonetaryHelper {
  public static BigDecimal getValorAltaPrecisao(double value) {
    BigDecimal valorAltaPrecisao = new BigDecimal(value, MathContext.DECIMAL64);

    return valorAltaPrecisao.setScale(2, RoundingMode.HALF_UP);
  }
}
