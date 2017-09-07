package facebook;

import java.util.*;

/**
1，mutual friends， 已知一个function可以return 给定某人的friends。 找出A B的mutual friends
2，friend recommendation。聊了大概十分钟对recommendation的想法。最后问题是给定person A 返回一个list 包含推荐给A的不认识的人。
顺序 按照与A的mutual friends 的数量排。
 */
public class FriendsQuestions {

	public List<Person> getRecommendation(Person p, List<Person> candidates) {
		RecommComparator recomm = new RecommComparator(p);
		Collections.sort(candidates, recomm);
		return candidates;
	}

	public List<Person> getRecommendation2(Person user, List<Person> candidates) {
		Collections.sort(candidates, new Comparator<Person>() {
			@Override
			public int compare(Person a, Person b) {
				return getMutualFriends(user, a).size() - getMutualFriends(user, b).size();
			}
		});
		return candidates;
	}

	public List<Person> getMutualFriends(Person a, Person b) {
		return new RecommComparator(null).getMutualFriends(a, b);
	}

	public List<Person> getFriends(Person p) {
		return p.friends;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class RecommComparator implements Comparator<Person> {
		Person person;
		RecommComparator(Person person) {
			this.person = new Person(person);
		}

		@Override
		public int compare(Person a, Person b) {
			return getMutualFriends(person, a).size() -
					getMutualFriends(person, b).size();
		}

		public List<Person> getMutualFriends(Person a, Person b) {
			PersonComparator comparator = new PersonComparator();
			Collections.sort(a.friends, comparator);
			Collections.sort(b.friends, comparator);
			List<Person> fa = a.friends;
			List<Person> fb = b.friends;
			int i = 0, j = 0;
			List<Person> res = new ArrayList<>();
			while (i < fa.size() && j < fb.size()) {
				if (fa.get(i).id == fb.get(j).id) {
					res.add(new Person(fa.get(i)));
					i++;
					j++;
				} else if (fa.get(i).id < fb.get(j).id) {
					i++;
				} else {
					j++;
				}
			}
			return res;
		}
	}

	class PersonComparator implements Comparator<Person> {
		@Override
		public int compare(Person a, Person b) {
			return a.id - b.id;
		}
	}

	static class Person {
		int id;
		List<Person> friends;
		Person(int id) {
			this.id = id;
			friends = new ArrayList<>();
		}
		Person(Person p) {
			this.id = p.id;
			friends = new ArrayList<>(p.friends);
		}
	}

}
