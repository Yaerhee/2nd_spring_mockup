package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    //AppConfig에 MemoryMemberRepository 주입 부분이 옮겨감
    //추상화에만 의존하게 됨 -> 생성자 주입을 통해 객체가 전달됨 -> DIP 원칙 지킴

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        //AppConfig 파일(설정 클래스) 구축으로 인해, 해당 위치에 MemoryMemberRepository가 간접 주입됨
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        //상단의 다형성 정의에 의해, 인터페이스가 아닌 MemoryMemberRepository에 있는 save method가 호출될 것
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
