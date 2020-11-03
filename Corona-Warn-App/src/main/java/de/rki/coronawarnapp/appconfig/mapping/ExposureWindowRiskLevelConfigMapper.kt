package de.rki.coronawarnapp.appconfig.mapping

import dagger.Reusable
import de.rki.coronawarnapp.appconfig.ExposureWindowRiskLevelConfig
import de.rki.coronawarnapp.server.protocols.internal.v2.AppConfigAndroid
import de.rki.coronawarnapp.server.protocols.internal.v2.RiskCalculationParametersOuterClass
import javax.inject.Inject

@Reusable
class ExposureWindowRiskLevelConfigMapper @Inject constructor() :
    ExposureWindowRiskLevelConfig.Mapper {

    override fun map(rawConfig: AppConfigAndroid.ApplicationConfigurationAndroid): ExposureWindowRiskLevelConfig {
        val riskCalculationParameters = rawConfig.riskCalculationParameters
        return ExposureWindowRiskLevelContainer(
            minutesAtAttenuationFilters = riskCalculationParameters
                .minutesAtAttenuationFiltersList,
            minutesAtAttenuationWeights = riskCalculationParameters
                .minutesAtAttenuationWeightsList,
            transmissionRiskLevelEncoding = riskCalculationParameters
                .trlEncoding,
            transmissionRiskLevelFilters = riskCalculationParameters
                .trlFiltersList,
            transmissionRiskLevelMultiplier = riskCalculationParameters
                .transmissionRiskLevelMultiplier,
            normalizedTimePerExposureWindowToRiskLevelMapping = riskCalculationParameters
                .normalizedTimePerEWToRiskLevelMappingList
        )
    }

    data class ExposureWindowRiskLevelContainer(
        override val minutesAtAttenuationFilters: List<RiskCalculationParametersOuterClass.MinutesAtAttenuationFilter>,
        override val minutesAtAttenuationWeights: List<RiskCalculationParametersOuterClass.MinutesAtAttenuationWeight>,
        override val transmissionRiskLevelEncoding: RiskCalculationParametersOuterClass.TransmissionRiskLevelEncoding,
        override val transmissionRiskLevelFilters: List<RiskCalculationParametersOuterClass.TrlFilter>,
        override val transmissionRiskLevelMultiplier: Double,
        override val normalizedTimePerExposureWindowToRiskLevelMapping:
        List<RiskCalculationParametersOuterClass.NormalizedTimeToRiskLevelMapping>
    ) : ExposureWindowRiskLevelConfig
}
