import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UserShelf implements Serializable{

	private static final long serialVersionUID = -4867077172051004917L;
	ArrayList<User> userList;
	
	public UserShelf(){
		userList = new ArrayList<>();
	}
	
	public UserShelf(User[] users) {
		this();
		for(User user: users) {
			userList.add(user);
		}
	}
	
	public void addUser(User newUser) {
		for(User user: userList) {
			if(user.getUserName().equals(newUser.getUserName())) {
				return;
			}
		}
		userList.add(newUser);
	}
	
	public void removeUser(User newUser) {
		for(User user: userList) {
			if(user.getID().equals(newUser.getID())) {
				userList.remove(newUser);
				return;
			}
		}
	}
	
	public ArrayList<User> getAllUsers(){
		return userList;
	}
	
    public static UserShelf read(UserShelf us){
		try (ObjectInputStream userLocation = new ObjectInputStream(new FileInputStream("users.dat"));) {
			User[] users = (User[]) userLocation.readObject();
			for (int i = 0; i < users.length; i++) {
				us.addUser(users[i]);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return us;
}
    
    public static void write(UserShelf us){
		User[] users = new User[us.getAllUsers().size()];

		for (int i = 0; i < users.length; i++) {
			users[i] = us.getAllUsers().get(i);
		}

		try (ObjectOutputStream bookLocation = new ObjectOutputStream(new FileOutputStream("users.dat"));) {
			bookLocation.writeObject(users);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
