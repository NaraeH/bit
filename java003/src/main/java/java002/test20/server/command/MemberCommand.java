package java002.test20.server.command;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java002.test20.server.Member;
import java002.test20.server.MemberDao;
import java002.test20.server.annotation.Command;
import java002.test20.server.annotation.Component;

@Component
public class MemberCommand {
	MemberDao memberDao;
	Scanner scanner;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	@Command("add")
	public void add(Map<String, Object> params){
		Member member = new Member();

		System.out.print("아이디:");
		member.setId(scanner.nextLine());
		
		System.out.print("이름:");
		member.setName(scanner.nextLine());

		System.out.print("비밀번호:");
		member.setPw(scanner.nextLine());

		System.out.print("이메일:");
		member.setEmail(scanner.nextLine());
		
		System.out.print("전화번호:");
		member.setTel(scanner.nextLine());

		System.out.print("팩스:");
		member.setPax(scanner.nextLine());
		
		System.out.print("상세주소:");
		member.setDatAddrs(scanner.nextLine());

		System.out.print("사진:");
		member.setPhoto(scanner.nextLine());
		
		System.out.print("주소:");
		member.setNo(scanner.nextInt());


		memberDao.insert(member);
		System.out.println("저장하였습니다.");
	}


	@Command("delete")
	public void delete(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		String id = options.get(0);

		Member member  = memberDao.selectOne(id);

		if(member == null){
			System.out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
			return;
		}

		System.out.print(member.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			memberDao.delete(id);
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("삭제 취소하였습니다.");
		}
	}

	@Command("list")
	public void list(HashMap<String, Object> params){
		PrintStream out = (PrintStream)params.get("out");
		
		int pageNo = 0;
		int pageSize = 0;


		if(params.get("pageNo") != null){
			pageNo = Integer.parseInt((String)params.get("pageNo"));
			pageSize = 3;
		}

		if(params.get("pageSize") != null){
			pageSize = Integer.parseInt((String)params.get("pageSize"));
		}

		for (Member member : MemberDao.selectList(pageNo, pageSize)) {
			System.out.printf("%-4s %-20s %10s %10s %10s %10s %11s \n",
					member.getId(),
					member.getName(),
					member.getEmail(),
					member.getTel(),
					member.getPax(),
					member.getPhoto(),
					member.getNo());
		}
		out.println();
	}

	@Command("update")
	public void update(HashMap<String, Object> params){
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		String id = options.get(0);

		Member member = MemberDao.selectOne(id);
		if(member == null){
			System.out.println("해당 인덱스의 데이터를 찾을 수 없습니다.");
			return;
		}

		Member tempMember = null;
		try {
			tempMember = member.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}

		String text = null;
		System.out.printf("패스워드(%s):", member.getPw());
		text = scanner.nextLine();
		System.out.println("===>" + text);
		if (text.length() > 0)
			tempMember.setPw(text);
		
		System.out.printf("이메일(%s):", member.getEmail());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setEmail(text);
		
		System.out.printf("이름(%s):", member.getName());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setName(text);

		System.out.printf("전화번호(%s):", member.getTel());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setTel(text);

		System.out.printf("팩스(%s):", member.getPax());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setPax(text);
		
		System.out.printf("상세주소(%s):", member.getDatAddrs());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setDatAddrs(text);
		
		System.out.printf("사진(%s):", member.getPhoto());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setPhoto(text);
		
		System.out.printf("주소(%s):", member.getNo());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempMember.setNo(Integer.parseInt(text));
		
		System.out.print("정말 변경하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			memberDao.update(tempMember);
			System.out.println("변경하였습니다.");
		} else {
			System.out.println("변경 취소하였습니다.");
		}

	}

	@Command("view")
	public void view(HashMap<String, Object> params) {
		ArrayList<String> options = (ArrayList<String>) params.get("options");
		String id = options.get(0);

		Member member = memberDao.selectOne(id);

		if (member == null) {
			System.out.println("해당 번호의 성적정보를 찾을 수 없습니다.");
			return;
		}

		System.out.println("아이디: " + member.getId());
		System.out.println("이름: " + member.getName());
		System.out.println("이메일: " + member.getEmail());
		System.out.println("전화번호: " + member.getTel());
		System.out.println("팩스: " + member.getPax());
		System.out.println("상세주소: " + member.getDatAddrs());
		System.out.println("주소: " + member.getNo());
	}
}
