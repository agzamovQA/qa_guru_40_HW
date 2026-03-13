package tests.homework.lesson_8.model;

public class ClientNameBankInfo {

    private String firstName;
    private Integer id;
    private ClientCardBankInfo cards;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientCardBankInfo getCards() {
        return cards;
    }

    public void setCards(ClientCardBankInfo cards) {
        this.cards = cards;
    }
}
