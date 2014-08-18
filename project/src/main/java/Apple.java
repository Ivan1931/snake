/**
 * Created by jonah-hooper on 2014/07/29.
 */
public class Apple extends Point {
    public Apple(String data) {
        //Replace space with comma since position values are separated by strings
        super(data.replace(' ', ','));
    }
}
