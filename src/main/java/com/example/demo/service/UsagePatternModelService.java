public interface UsagePatternModelService {

    UsagePatternModel createModel(UsagePatternModel model);

    UsagePatternModel updateModel(Long id, UsagePatternModel model);

    UsagePatternModel getModelForBin(Long binId);

    List<UsagePatternModel> getAllModels();
}
