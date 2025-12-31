import java.util.Objects;

public class Member {
    private final String memberId;   // unique
    private String name;
    private String email;

    public Member(String memberId, String name, String email) {
        if (memberId == null || memberId.trim().isEmpty())
            throw new IllegalArgumentException("Member ID cannot be empty.");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");

        this.memberId = memberId.trim();
        this.name = name.trim();
        this.email = (email == null) ? "" : email.trim();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "" : email.trim();
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                (email.isEmpty() ? "" : ", email='" + email + '\'') +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
