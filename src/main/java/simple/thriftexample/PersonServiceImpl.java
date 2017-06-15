package simple.thriftexample;

import org.apache.thrift.TException;
import thrift.DataException;
import thrift.Person;
import thrift.PersonService;

/**
 * @author hyperglory
 * @date 2017/6/15 14:39
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got Client Param: " + username);

        Person person = new Person();
        person.setUsername("glory");
        person.setAge(24);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param: ");

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
