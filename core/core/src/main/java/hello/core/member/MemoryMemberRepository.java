package hello.core.member;

import java.util.HashMap;
import java.util.Map;

//보통은 인터페이스와 구현체를 각각 다른 패키지에 두는 것이 좋음 (예제 구현 상 같은 패키지에 설계)
public class MemoryMemberRepository implements MemberRepository {
    
    //원래는 ConcurrentHashMap을 사용해서 동시성 issue 발생을 막는 것이 좋음(실무에서는 권장)
    private static Map<Long, Member> store = new HashMap<>();

    //상세한 오류 검증은 우선 생략(구현 먼저)
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
