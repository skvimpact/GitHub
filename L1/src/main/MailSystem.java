/**
 * Created by KSafonov on 14/09/2017.
 */
import java.util.logging.*;
public class MailSystem {
    public static void main(String[] args) {
        //System.out.println("Mail System is alive");
        //System.out.println(String.format("stones instead of {%s}", "ddd"));

        MailMessage mail1 = new MailMessage("Austin Powers", "government", "Send me some money");
        MailMessage mail2 = new MailMessage("Government","Austin Powers",  "We don't have any");
        MailMessage mail3 = new MailMessage("Man1","Man2",  "Let us drink beer");
        MailPackage mp =  new MailPackage("God",
                "Human",
                new Package("Divine gift",
                        999));
        Thief thief = new Thief(100);
        MailPackage newmp = (MailPackage) thief.processMail(mp);

        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(null);
        worker.processMail(newmp);


       // Inspector inspector = new Inspector();
       // inspector.processMail(newmp);
        Logger logger = Logger.getLogger("");

        //ConsoleHandler handler = new ConsoleHandler();
        //handler.setLevel(Level.ALL);
        //handler.setFormatter(new XMLFormatter());
        //logger.addHandler(handler);
        Spy spy = new Spy(logger);
        spy.processMail(mail1);
        spy.processMail(mp);
        spy.processMail(mail2);
        spy.processMail(newmp);
        spy.processMail(mail3);

    }
}


/*public static*/ interface Sendable {
    String getFrom();
    String getTo();
}

/*public static*/ abstract class AbstractSendable implements Sendable {

    protected final String from;
    protected final String to;

    public AbstractSendable(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSendable that = (AbstractSendable) o;

        if (!from.equals(that.from)) return false;
        if (!to.equals(that.to)) return false;

        return true;
    }

}

/*public static*/ class MailMessage extends AbstractSendable {

    private final String message;

    public MailMessage(String from, String to, String message) {
        super(from, to);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MailMessage that = (MailMessage) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

}

/*public static*/ class MailPackage extends AbstractSendable {
    private final Package content;

    public MailPackage(String from, String to, Package content) {
        super(from, to);
        this.content = content;
    }

    public Package getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MailPackage that = (MailPackage) o;

        if (!content.equals(that.content)) return false;

        return true;
    }

}
/*public static*/ class Package {
    private final String content;
    private final int price;

    public Package(String content, int price) {
        this.content = content;
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (price != aPackage.price) return false;
        if (!content.equals(aPackage.content)) return false;

        return true;
    }
}
/*public static*/ interface MailService {
    Sendable processMail(Sendable mail);
}
/*public static*/ class RealMailService implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}

class UntrustworthyMailWorker implements MailService{
   // private RealMailService realMailService;
    MailService[] mailServices;

    public UntrustworthyMailWorker(MailService[] mailServices){
        this.mailServices = mailServices;
    }

    public  Sendable processMail(Sendable mail){

        for(int i = 0; (mailServices != null) && (i < mailServices.length); i++)
        {
            mail = mailServices[i].processMail(mail);
        }
        return  getRealMailService().processMail(mail);
    }
    //public RealMailService getRealMailService() {return this.realMailService;}
    public RealMailService getRealMailService() {
        return new RealMailService();
    }
}

class Thief implements MailService{
    private int minValue;
    private int stolenValue = 0;

    public Thief(int minValue){
        this.minValue = minValue;
    }

    public  Sendable processMail(Sendable mail){
        if (mail instanceof MailPackage ){
            if (((MailPackage)mail).getContent().getPrice() >= minValue){
                stolenValue += ((MailPackage)mail).getContent().getPrice();
                return new MailPackage(mail.getFrom(),
                                       mail.getTo(),
                                       new Package(String.format("stones instead of {%s}", ((MailPackage)mail).getContent().getContent()),
                                                    0));
            }
        }
        return mail;
    }

    public int getStolenValue() {return stolenValue;}
}

class Spy implements MailService {
    private static Logger LOGGER;// = Logger.getLogger(Spy.class.getName());
    public Spy(Logger LOGGER){
        this.LOGGER = LOGGER;
    }
    public  Sendable processMail(Sendable mail){
        if (mail instanceof MailMessage) {
            if (mail.getFrom().equals(AUSTIN_POWERS) || mail.getTo().equals(AUSTIN_POWERS)) {
                LOGGER.log(Level.WARNING,
                        "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                        new Object[]{mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()}
                );
            } else {
                LOGGER.log(Level.INFO,
                        "Usual correspondence: from {0} to {1}",
                        new Object[]{mail.getFrom(), mail.getTo()}
                );
            }
        }

        return mail;
    }
    public static final String AUSTIN_POWERS = "Austin Powers";
}

class IllegalPackageException extends RuntimeException {
    public IllegalPackageException() {super("");}

}
class StolenPackageException extends RuntimeException {
    public StolenPackageException() {super("");}
}
class Inspector implements MailService{
    public  Sendable processMail(Sendable mail){
        if (mail instanceof MailPackage ) {
            String content = ((MailPackage)mail).getContent().getContent().toLowerCase();
            if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE))
                throw new IllegalPackageException();
            if (content.contains("stones"))
                throw new StolenPackageException();
        }
        return mail;
    }
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
}