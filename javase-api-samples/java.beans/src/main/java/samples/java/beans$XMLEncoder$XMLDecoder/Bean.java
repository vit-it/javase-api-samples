package samples.java.beans$XMLEncoder$XMLDecoder;

import java.time.LocalDateTime;

/**
 * Simple Pojo
 */
public class Bean {

    private Boolean f1;
    private boolean f2;
    private E f3;
    private LocalDateTime f4;
    private Inner f5;

    public Bean() {

    }

    public Boolean getF1() {
        return f1;
    }

    public void setF1(Boolean f1) {
        this.f1 = f1;
    }

    public boolean isF2() {
        return f2;
    }

    public void setF2(boolean f2) {
        this.f2 = f2;
    }

    public E getF3() {
        return f3;
    }

    public void setF3(E f3) {
        this.f3 = f3;
    }

    public LocalDateTime getF4() {
        return f4;
    }

    public void setF4(LocalDateTime f4) {
        this.f4 = f4;
    }

    public Inner getF5() {
        return f5;
    }

    public void setF5(Inner f5) {
        this.f5 = f5;
    }

    public enum E {
        C,
        F
    }

    public static class Inner {
        private Double i1;
        private Long i2;

        public Inner() {

        }

        public Double getI1() {
            return i1;
        }

        public void setI1(Double i1) {
            this.i1 = i1;
        }

        public Long getI2() {
            return i2;
        }

        public void setI2(Long i2) {
            this.i2 = i2;
        }
    }
}
