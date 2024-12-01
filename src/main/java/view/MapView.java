package view;

import interface_adapters.map.MapController;
import interface_adapters.map.MapState;
import interface_adapters.map.MapViewModel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for displaying a map with food and study space locations.
 */
public class MapView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Check Map";
    private final MapViewModel mapViewModel;

    private final JTextField nameInputField = new JTextField(15);
    private final JLabel nameErrorField = new JLabel();

    private final JTextField latitudeInputField = new JTextField(15);
    private final JLabel latitudeErrorField = new JLabel();

    private final JTextField longitudeInputField = new JTextField(15);
    private final JLabel longitudeErrorField = new JLabel();

    private final JButton viewMap;
    private final JButton back;

    private final JMapViewer mapViewer = new JMapViewer();
    private MapController mapController;

    public MapView(MapViewModel mapViewModel) {

        this.mapViewModel = mapViewModel;
        this.mapViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MapViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel locationName = new LabelTextPanel(
                new JLabel("Name"), nameInputField);
        final LabelTextPanel locationLat = new LabelTextPanel(
                new JLabel("Latitude"), latitudeInputField);
        final LabelTextPanel locationLong = new LabelTextPanel(
                new JLabel("Longitude"), longitudeInputField);

        final JPanel buttons = new JPanel();
        viewMap = new JButton("View Map");
        buttons.add(viewMap);
        back = new JButton("Back to list of reviews");
        buttons.add(back);

        viewMap.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        final MapState currentState = mapViewModel.getState();

                        mapController.execute(
                                currentState.getName(),
                                currentState.getLatitude(),
                                currentState.getLongitude()
                        );
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mapController.switchToListReviewView();
                    }
                }
        );

        nameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final MapState currentState = mapViewModel.getState();
                currentState.setName(nameInputField.getText());
                mapViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        latitudeInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final MapState currentState = mapViewModel.getState();
                currentState.setLatitude(latitudeInputField.getText());
                mapViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        longitudeInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final MapState currentState = mapViewModel.getState();
                currentState.setLongitude(longitudeInputField.getText());
                mapViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBorder(BorderFactory.createTitledBorder("Map Viewer"));
        mapViewer.setZoomControlsVisible(true);

        mapPanel.add(mapViewer, BorderLayout.CENTER);

        this.add(title);
        this.add(locationName);
        this.add(nameErrorField);
        this.add(locationLat);
        this.add(latitudeErrorField);
        this.add(locationLong);
        this.add(longitudeErrorField);
        this.add(buttons);
        this.add(mapPanel);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    private void addMarkerToMap(double latitude, double longitude) {
        // Clear any previous markers
        mapViewer.removeAllMapMarkers();

        // Create a new MapMarker and add it to the map
        Coordinate coordinate = new Coordinate(latitude, longitude);
        MapMarkerDot marker = new MapMarkerDot(coordinate);
        mapViewer.addMapMarker(marker);

        // Center the map on the new coordinates
        mapViewer.setDisplayPosition(coordinate, 10);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MapState state = (MapState) evt.getNewValue();
        setFields(state);
        nameErrorField.setText(state.getNameError());
        latitudeErrorField.setText(state.getLatitudeError());
        longitudeErrorField.setText(state.getLongitudeError());

        addMarkerToMap(Double.parseDouble(state.getLatitude()), Double.parseDouble(state.getLongitude()));
    }

    private void setFields(MapState state) {
        nameInputField.setText(state.getName());
        latitudeInputField.setText(state.getLatitude());
        longitudeInputField.setText(state.getLongitude());
    }

    public String getViewName() {
        return viewName;
    }

    public void setMapController(MapController controller) {
        this.mapController = controller;
    }
}