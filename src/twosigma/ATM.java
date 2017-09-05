package twosigma;

import java.util.*;

/*
 * 1. 设计ATM，给你写好了interface class自己写api method，最后写下ATM class几个方法的实现，开户，登陆，输密码输错五次吞卡，存款取款，打印收据，没打印纸发消息给分行经理等等。
 * 关于密码输错连续五次楼主跟面试官讨论下简单的while (attemp < 5)不好，需要设计个rate limiter限制是一天之内还是1小时之内连续错再分别处理
 * 
 * 2. ATM design是写成interface而不是class 所以不需要写class object什么的 你这样写大叔会纠正你 而且很不高兴
 * 大叔先上来写了几个方面：print, display, cardInsert, accountService ...
 * 
 */
public class ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

class User {
	String name;
}

enum Status {
	ACTIVE,
	BLOCKED,
	DELETED;
}

class BankAccount {
	String bankAccountNumber;
	User user;
	double balance;
	String password;
	boolean isBlocked;
	TreeSet<Calendar> failureTimeStamps = new TreeSet<Calendar>();
	void clean(Calendar date) {
		Calendar oldDate = date;
	    oldDate.set(Calendar.DAY_OF_MONTH, date.DAY_OF_MONTH - 1);
	    Iterator it = failureTimeStamps.iterator();
	    while(it.hasNext()) {
	    	if(oldDate.after((Calendar)it.next())) {
	    		it.remove();
	    	} else {
	    		break;
	    	}
	    }
	}
}

interface EmailService {}

class ATMServiceImpl implements ATMService {
	private Map<String, BankAccount> bankAccountTable;
	Random randGen;
	private final EmailService emailService;
	
	public ATMServiceImpl(EmailService emailService) {
		bankAccountTable = new HashMap<>();
		randGen = new Random();
		this.emailService = emailService;
	}

	@Override
	public void deposit(BankAccount bankAccount, double amount) {
		bankAccount.balance += amount;
		display(String.format("You deposit %f, and your current balance is %f", amount, bankAccount.balance));
	}

	@Override
	public void display(String message) {
		System.out.println(message);
	}

	@Override
	public void emailManager(String message) {
		display(message);
	}

	@Override
	public BankAccount enroll(User user, String password) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.bankAccountNumber = generateBankAccountNumber();
		bankAccount.user = user;
		bankAccount.password = password;
		bankAccountTable.put(bankAccount.bankAccountNumber, bankAccount);
		return bankAccount;
	}

	private String generateBankAccountNumber() {
		String num = String.valueOf(randGen.nextInt(100));
		while (bankAccountTable.containsKey(num)) {
			num = String.valueOf(randGen.nextInt(100));
		}
		return num;
	}

	@SuppressWarnings("deprecation")
	@Override
	public BankAccount login(String bankAccountNumber, String password) {
		if (!bankAccountTable.containsKey(bankAccountNumber)) {
			display(String.format("Bank account %d not exist!", bankAccountNumber));
			return null;
		}
		BankAccount bankAccount = bankAccountTable.get(bankAccountNumber);
		if (bankAccount.isBlocked) {
			display("blocked");
			return null;
		}
		if (!bankAccount.password.equals(password)) {
			Calendar date = Calendar.getInstance();
			bankAccount.failureTimeStamps.add(date);
			bankAccount.clean(date);
			Calendar oneHourDate = date;
			oneHourDate.set(Calendar.HOUR, date.HOUR - 1);
			SortedSet<Calendar> subset = bankAccount.failureTimeStamps.subSet(oneHourDate, date);
			if(subset.size() >= 5) { 
				display("Try tomorrow");
			}
			Calendar oneDayDate = date;
			oneDayDate.set(Calendar.DAY_OF_MONTH, date.DAY_OF_MONTH - 1);
			subset = bankAccount.failureTimeStamps.subSet(oneDayDate, date);
			if(subset.size() >= 10) {
				cardInvalid(bankAccount);
			}
			display("Invalid bank account number or password!");
			return null;
		}
		return bankAccount;
	}

	@Override
	public void withdraw(BankAccount bankAccount, double amount) {
		if(bankAccount.balance < amount) {
			display("No suffice balance");
		}
		bankAccount.balance -= amount;
		display(String.format("You withdraw %f, and your current balance is %f", amount, bankAccount.balance));
	}

	@Override
	public void cardInvalid(BankAccount bankAccount) {
		bankAccount.isBlocked = true;
		display(String.format("Your card with account number %s is blocked due to security reason", bankAccount.bankAccountNumber));
	}
}

interface ATMService {

	BankAccount enroll(User user, String password);

	BankAccount login(String bankAccountNumber, String password);

	void withdraw(BankAccount bankAccount, double amount);
	
	void deposit(BankAccount bankAccount, double amount);

	void display(String message);

	void emailManager(String message);
	
	void cardInvalid(BankAccount bankAccount);
}
