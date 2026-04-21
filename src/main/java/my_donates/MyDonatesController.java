package my_donates;

import components.Components;

public class MyDonatesController extends Components {

    public void initialize() {
        System.out.println(MyDonatesService.getDonatesUserById());
    }
}
