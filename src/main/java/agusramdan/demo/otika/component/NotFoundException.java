package agusramdan.demo.otika.component;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }

    public NotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
