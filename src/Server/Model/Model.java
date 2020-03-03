package Server.Model;

/**
 * Model class responsible for data handling and manipulation
 */
public class Model {

    /**
     * Default constructor
     */
    public Model(){};

    /**
     * Concatinates and returns the result
     * @param s1
     * @param s2
     * @return
     */
    public String concatenate (String s1, String s2)
    {
        return (s1 + s2);
    }

    /**
     * Converts to upper case as well as concatinates
     * @param s1
     * @param s2
     * @return
     */
    public String toUpperCase(String s1,String s2){
        return (s1.toUpperCase() + s2.toUpperCase());
    }

    /**
     * Converts to lowercase as well as concatinates
     * @param s1
     * @param s2
     * @return
     */
    public String tolowerCase(String s1,String s2){
        return (s1.toLowerCase() + s2.toLowerCase());
    }
}