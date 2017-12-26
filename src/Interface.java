public class Interface {
    public boolean canMove(String bewegungString, Map spielfeld) {
        char[] bewegungListe = bewegungString.toCharArray();
        if(checkElements(bewegungListe)) {
            checkOrientierung(bewegungListe, spielfeld);


        }

    }
    public Boolean checkOrientierung(char[] bewegungListe, Map spielfeld) {
        //
    }
    private Boolean checkElements (char[] bewegungListe) {
        // liste lange
        // typ
        for (char bewegungChar: bewegungListe) {
            switch (bewegungChar) {
                case 'a':
                    break;
                case 'w':
                    break;
                case 's':
                    break;
                case 'd':
                    break;
                default:
                    return false;
            }
            return true;
        }
    }
}
