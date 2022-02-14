/* Add the jar files from activeMQ server*/
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.jms.Connection;
import java.jms.ConnectionFactory;
import java.jms.JMSException;
import java.jms.Session;
import java.jms.Textmessage;
import java.jms.Topic;
import java.jms.Message;
import java.jms.MessageConsumer;
import java jms.*;
import org,apache.activemq.ActiveMQConnectionFactory;
public class ReadMessageAMQ {
   

    public static void main(String[] args) throws Exception {
PrintWriter fw =new FileWriter("Path of the output file");
printWriter out= new printWriter(fw);
        int time_to_run=Integer.parseint(args0])*60000;
        String Queue_name=args[1];
        Properties prop = new Properties();InputStream input=null;
        String myurl=null;
        try{
            input =new FileInputStream("Path to properties file");
            prop.load(input);
            myurl=prop.getProperty("URL");
             out.println("The target server address is :" + myurl +"\n");
        } catch(IOException ex)
        { out.println("Could not load the properties file");
         } //end of try catch
    ConnectionFactory connectionfactory =new ActiveMQConnectionfactory(myurl);
    Connection connection =connectionfactory.createConnection();
    Session session =connection.createSession(false,session.Auto_ACKNOWLEDGE);
    Queue queue = session.createTopic(Queue_name);
    MessageConsumer consumer=session.createConsumer(queue);
    connection.start();

    int message_count=0;
    while(true){

        message msg=consumer.receive(time_to_run);
        if (msg instanceof TextMessage){
            ++message_count;
            TextMessage tm=(TextMessage) msg;
            out.println("Message number :"+message_count +":");
          
            out.println(tm.getText());
           out.println("The message properties are :");
           out.println("Property1"+tm.getStringProperty("property1"));
            out.ptintln("Property2"+tm.getStringProperty("Propertty2"));
            msg.cknowledge();
        } else {
           // System.out.println("\n\nQueue Empty / No more message to recieve !.");
        out.println("\n\nQueue Empty / No more message to recieve !.");
            connection.stop();
            break;
        } //end else if else
     

        }//end of while
      out.println("The total number of messages recieved is" + message_count +".");
        connection.close();
        out.println("Connection closed");
      out.close();
    }//end of main
}
 //end of class