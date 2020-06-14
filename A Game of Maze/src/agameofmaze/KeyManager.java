
package agameofmaze;

/**
 * This class manages the key inputs
 * the user makes to move the player.
 */

class KeyManager {//Start of class KeyManager.

    public static void manageKeyInputs(){//Start of method manageKeyInputs.

        //Managing key pressed inputs.
        Run.getPlayer().getHero().setOnKeyPressed(e -> {

            switch(e.getCode()){//Start of switch.

                case UP:    Run.getPlayer().up    = true; break;
                case DOWN:  Run.getPlayer().down  = true; break;
                case LEFT:  Run.getPlayer().left  = true; break;
                case RIGHT: Run.getPlayer().right = true; break;

            }//End of switch.

        });//End of KeyPressed.

        //Managing key released inputs.
        Run.getPlayer().getHero().setOnKeyReleased(e -> {

            switch(e.getCode()){//Start of switch.

                case UP:    Run.getPlayer().up    = false; break;
                case DOWN:  Run.getPlayer().down  = false; break;
                case LEFT:  Run.getPlayer().left  = false; break;
                case RIGHT: Run.getPlayer().right = false; break;

            }//End of switch.

        });//End of KeyReleased.

    }//End of method manageKeyInputs.


}//End of class KeyManager.
