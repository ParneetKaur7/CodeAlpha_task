package blogs_program;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HotelReviewApp extends JFrame {
    private ReviewManager reviewManager;
    private JComboBox<String> hotelSelector;
    private JTextArea reviewDisplayArea;
    private JTextField usernameField;
    private JTextField ratingField;
    private JTextArea commentField;

    public HotelReviewApp() {
        super("Hotel Review System");
        this.reviewManager = new ReviewManager();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 100);
        setSize(800, 500);
        setLayout(new BorderLayout());

        // Hotel data and UI elements
        Hotel hotel1 = new Hotel("Grand Plaza");
        Hotel hotel2 = new Hotel("Ocean View");
        reviewManager.addHotel(hotel1);
        reviewManager.addHotel(hotel2);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        hotelSelector = new JComboBox<>();
        hotelSelector.addItem(hotel1.getName());
        hotelSelector.addItem(hotel2.getName());
        hotelSelector.addActionListener(e -> updateReviewsDisplay());
        topPanel.add(hotelSelector);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Rating (1.0 to 5.0):"));
        ratingField = new JTextField();
        inputPanel.add(ratingField);
        inputPanel.add(new JLabel("Comment:"));
        commentField = new JTextArea(2, 20);
        inputPanel.add(new JScrollPane(commentField));
        JButton submitButton = new JButton("Add Review");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReview();
            }
        });
        inputPanel.add(submitButton);

        reviewDisplayArea = new JTextArea();
        reviewDisplayArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(reviewDisplayArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addReview() {
        String username = usernameField.getText();
        double rating = Double.parseDouble(ratingField.getText());
        String comment = commentField.getText();
        String hotelName = (String) hotelSelector.getSelectedItem();
        Hotel hotel = reviewManager.findHotel(hotelName);
        if (hotel != null) {
            hotel.addReview(new Review(username, rating, comment));
            updateReviewsDisplay();  
        }
    }

    private void updateReviewsDisplay() {
        String selectedHotel = (String) hotelSelector.getSelectedItem();
        List<Review> reviews = reviewManager.getReviewsSortedByRating(selectedHotel);
        StringBuilder builder = new StringBuilder();
        for (Review review : reviews) {
            builder.append("Rating: ").append(review.getRating()).append(", ");
            builder.append("User: ").append(review.getUsername()).append(", ");
            builder.append("Comment: ").append(review.getComment()).append("\n");
        }
        reviewDisplayArea.setText(builder.toString());
    }

    public static void main(String[] args) {
        new HotelReviewApp();
    }
}

class Hotel {
    private String name;
    private List<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.reviews = new java.util.ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }
}

class Review {
    private String username;
    private double rating;
    private String comment;

    public Review(String username, double rating, String comment) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}

class ReviewManager {
    private List<Hotel> hotels;

    public ReviewManager() {
        hotels = new java.util.ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public Hotel findHotel(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }
        return null;
    }

    public List<Review> getReviewsSortedByRating(String hotelName) {
        Hotel hotel = findHotel(hotelName);
        if (hotel != null) {
            List<Review> reviews = new java.util.ArrayList<>(hotel.getReviews());
            reviews.sort((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()));
            return reviews;
        }
        return new java.util.ArrayList<>();
    }
}
