package vaermail;

import java.util.concurrent.ConcurrentLinkedDeque;
import org.springframework.stereotype.Service;


@Service
public class MailingList {


	private static ConcurrentLinkedDeque<MailInfo> mailList = new ConcurrentLinkedDeque<MailInfo>(); 
	
	void add(MailInfo mi){
		mailList.add(mi);
	}
	
	MailInfo get(int id){
		if(id > mailList.size())
			return new MailInfo("empty");
		int i = 0;
		for (MailInfo mi : mailList) {
			if(i++ == id)
				return mi;	
		}
		return new MailInfo("empty");
	}
	
	 ConcurrentLinkedDeque<MailInfo> getList(){
		 
		 return mailList;
	 }
	 
	 int size(){
		 return mailList.size();
	 }

	public MailInfo del(int id) {
		if(id > mailList.size())
			return new MailInfo("empty");
		int i = 0;
		for (MailInfo mi : mailList) {
			if(i++ == id){
				mailList.remove(mi);
				return mi;
			}
				
		}
		return new MailInfo("empty");
	}

}
