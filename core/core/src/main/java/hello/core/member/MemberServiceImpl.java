package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //구현객체 선택
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //Ctrl + Shift + Enter로 세미콜론과 함께 개행

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
