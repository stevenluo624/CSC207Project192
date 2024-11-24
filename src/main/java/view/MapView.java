package view;

import interface_adapters.map.MapController;
import interface_adapters.map.MapState;
import interface_adapters.map.MapViewModel;
import org.checkerframework.checker.units.qual.C;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for displaying a map with food and study space locations.
 */
public class MapView extends JPanel implements ActionListener, PropertyChangeListener {

    private final MapViewModel mapViewModel;

    private final JLabel mapName = new JLabel("Campus Food and Study Space Rater Map Viewer");
    private final JButton backButton = new JButton("Back"); // Button to go back to the list of reviews.

    private JMapViewer mapViewer; // Map viewer instance.
    private MapController mapController;

    public MapView(MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
        this.mapViewModel.addPropertyChangeListener(this);

        // Configure layout
        this.setLayout(new BorderLayout());

        // Map name label
        mapName.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(mapName, BorderLayout.NORTH);

        // Initialize map viewer
        mapViewer = new JMapViewer();
        mapViewer.setZoomControlsVisible(false); // Deprecated method replaced.
        addMapMarkers();

        this.add(mapViewer, BorderLayout.CENTER);

        // Add buttons panel
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(backButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);

        // Set up button actions
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            final MapState currentState = mapViewModel.getState();

                            mapController.execute(
                                    currentState.getName(),
                                    currentState.getLatitude(),
                                    currentState.getLongitude()
                            );
                        }
                    }
                }
        );
    }

    /**
     * Adds Location Marker to the Map
     */
    private void addMapMarkers() {

        final MapState currentState = mapViewModel.getState();
        String buildingName = currentState.getName();
        double buildingLat = currentState.getLatitude();
        double buildingLong = currentState.getLongitude();

        Coordinate coordinate = new Coordinate(buildingLat, buildingLong);
        MapMarkerDot marker = new MapMarkerDot(coordinate);
        marker.setBackColor(Color.RED);
        mapViewer.addMapMarker(marker);

    }

    /**
     * Handles button clicks.
     *
     * @param evt the ActionEvent to handle
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click" + evt.getActionCommand());
    }

    /**
     * Reacts to property changes in the map view model.
     *
     * @param evt the PropertyChangeEvent to handl
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property change logic (if needed)
        System.out.println("Property change detected: " + evt.getPropertyName());
    }

    public void setMapController(MapController controller) {
        this.mapController = controller;
    }
}