package controllers.FiberTools;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FiberController {

    @GetMapping("/fiber/{count}")
    public String generateFiber(@PathVariable int count) {
        String[] colors = {"Blue", "Orange", "Green", "Brown", "Slate", "White", "Red", "Black", "Yellow", "Violet", "Rose", "Aqua"};
        String[] tubes = {"Blue", "Orange", "Green", "Brown", "Slate", "White", "Red", "Black", "Yellow", "Violet", "Rose", "Aqua"};

        if (count < 1 || count > 144) {
            return "Invalid fiber count. Please enter a number between 1 and 144.";
        }

        String fiberColor = colors[(count - 1) % 12];
        String tubeColor = tubes[(count - 1) / 12];

        return fiberColor + " Fiber, " + tubeColor + " Tube";
    }

}

