package thyscom.spiffgis.mapviewer;

import java.io.File;
import java.io.IOException;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.swing.JMapPane;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.openide.util.Exceptions;

/**
 * A mapviewer pane for use by a topcomponent.
 * @author thys
 */
public class MapViewerPane extends JMapPane {

    public File getDataStoreFromDisk() {
        // display a data store file chooser dialog for shapefiles
        return JFileDataStoreChooser.showOpenFile("shp", null);
    }

    public void loadMap(File file) {
        try {
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();

            // Create a map context and add our shapefile to it
            MapContext map = new DefaultMapContext();
            map.setTitle("Quickstart");
            map.addLayer(featureSource, null);
            setMapContext(map);
            setRenderer(new StreamingRenderer());
            reset();


        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
