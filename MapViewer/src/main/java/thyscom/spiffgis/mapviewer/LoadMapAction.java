package thyscom.spiffgis.mapviewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "thyscom.spiffgis.mapviewer.LoadMapAction")
@ActionRegistration(displayName = "#CTL_LoadMapAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300)
})
@Messages("CTL_LoadMapAction=Load Map")
public final class LoadMapAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File f = getDataStoreFromDisk();
        if(f != null) {
            MapViewerTopComponent tc = new MapViewerTopComponent();
            tc.getMapViewerPane().loadMap(f);
            tc.open();
            tc.requestActive();
        }
    }
    
     private File getDataStoreFromDisk() {
        // display a data store file chooser dialog for shapefiles
        return JFileDataStoreChooser.showOpenFile("shp", null);
    }
}
