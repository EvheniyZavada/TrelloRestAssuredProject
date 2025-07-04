package consts;

public class ConstsHolder {
    public static final String userName = "zavada1997@gmail.com";
    public static final String myBoardId = "684497a8f96fc4515a307c4d";
    public static final String boardId = "68499967310c9b0128bb22c1";
    public static final String invalidBoardId = "68499967310c9b0128bb22c7";
    public static final String invalidShortBoardId = "68499967310c9b0128bb22c";
    public static final String invalidLongBoardId = "68499967310c9b0128bb22c17";

    public static final String myBoardNeedToDoListId = "684497a8f96fc4515a307c9e";
    public static final String myBoardInProgressListId = "684497a8f96fc4515a307c9f";
    public static final String myBoardDoneListId = "684497a8f96fc4515a307ca0";

    public static final String theirBoardCardId = "68345fadb7caea7fdd9104e3";
    public static final String invalidCardId = "68345fadb7caea7fdd9104e9";
    public static final String invalidShortCardId = "68345fadb7caea7fdd9104e";
    public static final String invalidLongCardId = "68499967310c9b0128bb22c171";
    public static final String TrelloKeyValue = "4d9dd97638a81eaec3d5a7f125b6b562";
    public static final String TrelloTokenValue = "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B";

    public static final String getBoardsEndpoint = "/members/{id}/boards";
    public static final String getBoardEndpoint = "/boards/{id}";
    public static final String getListsEndpoint = "/boards/{id}/lists";
    public static final String getCardsEndpoint = "/lists/{id}/cards";
    public static final String getCardEndpoint = "/cards/{id}";

    public static final String createBoardEndpoint = "/boards/";
    public static final String createCardEndpoint = "/cards";

    public static final String deleteBoardEndpoint = "/boards/{id}";
    public static final String deleteCardEndpoint = "/cards/{id}";

}



