package school;

public class NotFoundException extends RuntimeException {
	public NotFoundException() {
		super("The requested Information is not accessible.");
	}
}
