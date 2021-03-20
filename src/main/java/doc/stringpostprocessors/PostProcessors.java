package doc.stringpostprocessors;

import java.util.Map;

public class PostProcessors {
    public StringPostProcessor stringPostProcessor(String name){
        switch (name){
            case "Mytishchi":
                return new MytishchiStringPostProcessor();
            case "Dubrovka":
                return new DubrovkaStringPostProcessor();
            default:
                return new DefaultStringPostProcessor();
        }
    }
}
