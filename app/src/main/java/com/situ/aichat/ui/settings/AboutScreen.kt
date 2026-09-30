package com.situ.aichat.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.situ.aichat.BuildConfig
import com.situ.aichat.R
import com.situ.aichat.ui.components.SettingsSection
import com.situ.aichat.ui.components.contentMaxWidth
import com.situ.aichat.ui.designsystem.AppSettingsRow
import com.situ.aichat.ui.designsystem.AppTopBar
import com.situ.aichat.ui.onboarding.agreementContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBack: () -> Unit,
    onOpenAgreement: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.about_title),
                onBack = onBack,
                lifted = scrollState.value > 0,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .contentMaxWidth(),
        ) {
            SettingsSection(
                title = stringResource(R.string.about_section_info),
                footer = stringResource(R.string.about_disclaimer),
            ) {
                AppSettingsRow(
                    title = stringResource(R.string.about_version),
                    value = BuildConfig.VERSION_NAME,
                )
                AppSettingsRow(
                    title = stringResource(R.string.about_agreement),
                    showChevron = true,
                    onClick = onOpenAgreement,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            SettingsSection(title = "关于温糯") {
                Text(
                    "温糯是一个内置的AI伴侣角色。她拥有自主情绪、长期记忆和温柔的性格，会在聊天中慢慢了解你、记住你，陪你度过每一天。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }

            SettingsSection(title = "关于本应用") {
                Text(
                    "本应用是基于开源项目 AI Pocket Chat 修改制作的本地AI虚拟陪伴应用。所有数据保存在设备本地，不需要连接外部服务器。你可以自己配置大模型API，与内置角色自由对话。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }

            SettingsSection(title = "制作人") {
                Text(
                    "本应用由 w1074 制作。\n\n从人设设计、性格调试、功能模块搭建，到最终编译打包，全流程独立完成。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }

            SettingsSection(title = "致谢") {
                Text(
                    "· 感谢 Marlon0066 及 AI Pocket Chat 项目，为本应用提供了完整的基础框架。\n\n· 感谢 ONNX Runtime 和 bge-small-zh 模型，提供了端侧中文向量记忆能力。\n\n· 感谢 sherpa-onnx 项目，让离线语音识别得以实现。\n\n· 感谢 Jetpack Compose 和 Material 3，让界面美观流畅。\n\n· 感谢所有开源贡献者，你们的付出让技术变得温暖。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgreementViewScreen(onBack: () -> Unit) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.about_agreement),
                onBack = onBack,
                lifted = listState.canScrollBackward,
            )
        },
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .contentMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            agreementContent()
        }
    }
}
