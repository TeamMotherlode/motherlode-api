package motherlode.base.impl.resource;

import java.util.ArrayList;
import java.util.List;
import motherlode.base.api.resource.AssetsManager;
import motherlode.base.api.resource.function.AssetsGenerator;
import motherlode.base.api.resource.function.DataGenerator;

public class AssetsManagerImpl implements AssetsManager {
    public static final AssetsManagerImpl INSTANCE = new AssetsManagerImpl();

    private List<AssetsGenerator> assetGenerators = new ArrayList<>();
    private List<DataGenerator> dataGenerators = new ArrayList<>();

    @Override
    public void addAssets(AssetsGenerator p) {
        this.assetGenerators.add(p);
    }

    public List<AssetsGenerator> getAssets() {
        return this.assetGenerators;
    }

    public void removeAssetProcessorList() {
        this.assetGenerators = null;
    }

    @Override
    public void addData(DataGenerator p) {
        this.dataGenerators.add(p);
    }

    public List<DataGenerator> getData() {
        return this.dataGenerators;
    }

    public void removeDataProcessorList() {
        this.dataGenerators = null;
    }
}
