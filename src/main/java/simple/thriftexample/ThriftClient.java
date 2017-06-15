package simple.thriftexample;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.Person;
import thrift.PersonService;

/**
 * @author hyperglory
 * @date 2017/6/15 14:55
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByUsername("glory");
            System.out.println(person);

            System.out.println("--------");

            Person person1 = new Person();
            person1.setUsername("amoya");
            person1.setAge(24);
            person1.setMarried(true);

            client.savePerson(person1);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            transport.close();
        }
    }
}
