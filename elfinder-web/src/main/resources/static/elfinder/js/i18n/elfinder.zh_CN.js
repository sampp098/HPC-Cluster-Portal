/**
 * 简体中文 translation
* @author Translator deerchao <deerchao@gmail.com>
 * @author Andy Hu <andyhu7@yahoo.com.hk>
 * @author Max Wen<max.wen@qq.com>
 * @author Kejun Chang <changkejun@hotmail.com>
 * @author LDMING <china-live@live.cn>
 * @version 2018-01-26
 */
(function(root, factory) {
If (typeof define === 'function' && define.amd) {
Define(['elfinder'], factory);
} else if (typeof exports !== 'undefined') {
Module.exports = factory(require('elfinder'));
} else {
Factory(root.elFinder);
}
}(this, function(elFinder) {
elFinder.prototype.i18.zh_CN = {
Translator : 'Translator deerchao &lt;deerchao@gmail.com&gt;, Andy Hu &lt;andyhu7@yahoo.com.hk&gt;, Max Wen&lt;max.wen@qq.com&gt;, Kejun Chang &lt;changkejun@hotmail.com&gt; , LDMING &lt;china-live@live.cn&gt;',
Language : 'Simplified Chinese',
Direction : 'ltr',
dateFormat : 'Y-m-d H:i', // Mar 13, 2012 05:27 PM
fancyDateFormat : '$1 H:i', // will produce smth like: Today 12:25 PM
nonameDateFormat : 'ymd-His', // to apply if upload file is noname: 120513172700
Messages : {

	/********************************** errors ************** ********************/
	'error' : 'Error',
	'errUnknown' : 'Unknown error.',
	'errUnknownCmd' : 'Unknown command.',
	'errJqui' : 'Invalid jQuery UI configuration, must include Selectable, draggable, and droppable components.',
	'errNode' : 'elFinder needs to be able to create DOM elements.',
	'errURL' : 'Invalid elFinder configuration! URL option not configured.',
	'errAccess' : 'Access denied.',
	'errConnect' : 'Cannot connect to the server.',
	'errAbort' : 'Connection aborted.',
	'errTimeout' : 'Connection timeout.',
	'errNotFound' : 'The server side was not found.',
	'errResponse' : 'Invalid server-side response.',
	'errConf' : 'Invalid server-side configuration.',
	'errJSON' : 'PHP JSON module not installed.',
	'errNoVolumes' : 'No readable volume.',
	'errCmdParams' : 'Invalid command "$1".',
	'errDataNotJSON' : 'The data returned by the server does not conform to the JSON format.',
	'errDataEmpty' : 'The data returned by the server is empty.',
	'errCmdReq' : 'The server side request requires a command name.',
	'errOpen' : 'Unable to open "$1".',
	'errNotFolder' : 'The object is not a folder.',
	'errNotFile' : 'The object is not a file.',
	'errRead' : 'Unable to read "$1".',
	'errWrite' : 'Unable to write "$1".',
	'errPerm' : 'No permission.',
	'errLocked' : '"$1" is locked, cannot be renamed, moved or deleted.',
	'errExists' : 'file '$1" already exists.',
	'errInvName' : 'Invalid filename.',
	'errInvDirname' : 'Invalid folder name.', // from v2.1.24 added 12.4.2017
	'errFolderNotFound' : 'The folder does not exist.',
	'errFileNotFound' : 'The file does not exist.',
	'errTrgFolderNotFound' : 'The destination folder '$1".' was not found.
	'errPopup' : 'The browser blocked the popup. Please allow pop-ups in the options.',
	'errMkdir' : 'Cannot create folder '$1".',
	'errMkfile' : 'Cannot create file '$1".',
	'errRename' : 'Cannot rename "$1".',
	'errCopyFrom' : 'Do not allow copying from volume "$1".',
	'errCopyTo' : 'Do not allow copying to volume "$1".',
	'errMkOutLink' : 'Unable to create a link to a link other than the volume root.', // from v2.1 added 03.10.2015
	'errUpload' : 'Upload error.', // old name - errUploadCommon
	'errUploadFile' : 'Unable to upload '$1".', // old name - errUpload
	'errUploadNoFiles' : 'The file to be uploaded was not found.',
	'errUploadTotalSize' : 'Data exceeds the maximum allowed size.', // old name - errMaxSize
	'errUploadFileSize' : 'The file exceeds the maximum allowed size.', // old name - errFileMaxSize
	'errUploadMime' : 'File type not allowed.',
	'errUploadTransfer' : '"$1" Transfer error.',
	'errUploadTemp' : 'Unable to create temporary file for uploaded file.', // from v2.1 added 26.09.2015
	'errNotReplace' : ' "$1" already exists and cannot be replaced. ', // new
	'errReplace' : 'Cannot replace "$1".',
	'errSave' : 'Unable to save '$1".',
	'errCopy' : 'Cannot copy "$1".',
	'errMove' : 'Unable to move '$1".',
	'errCopyInItself' : 'Cannot move "$1" to the original location.',
	'errRm' : 'Cannot delete "$1".',
	'errTrash' : 'Cannot move to the recycle bin.', // from v2.1.24 added 30.4.2017
	'errRmSrc' : 'Cannot delete source file.',
	'errExtract' : 'Unable to extract file from '$1'.',
	'errArchive' : 'Unable to create a tarball.',
	'errArcType' : 'Unsupported compression format.',
	'errNoArchive' : 'The file is not a compressed package, or the compression format is not supported.',
	'errCmdNoSupport' : 'The server does not support this command.',
	'errReplByChild' : 'Cannot replace folder "$1" itself with the item under the folder "$1".
	'errArcSymlinks' : 'For security reasons, it is not allowed to decompress compressed packages containing symbolic links.', // edited 24.06.2012
	'errArcMaxSize' : 'The compressed package file exceeds the maximum allowed file size range.',
	'errResize' : 'Cannot resize to "$1".',
	'errResizeDegree' : 'Invalid rotation angle.', // added 7.3.2013
	'errResizeRotate' : 'Unable to rotate image.', // added 7.3.2013
	'errResizeSize' : 'Invalid image size.', // added 7.3.2013
	'errResizeNoChange' : 'The image size has not changed.', // added 7.3.2013
	'errUsupportType' : 'Unsupported file format.',
	'errNotUTF8Content' : 'file '$1" is not in UTF-8 format, can't be edited.', // added 9.11.2011
	'errNetMount' : 'Unable to load '$1".', // added 17.04.2012
	'errNetMountNoDriver' : 'This protocol is not supported.', // added 17.04.2012
	'errNetMountFailed' : 'Load failed.', // added 17.04.2012
	'errNetMountHostReq' : 'requires a host.', // added 18.04.2012
	'errSessionExpires' : 'Your session has expired due to long periods of inactivity.',
	'errCreatingTempDir' : 'Unable to create temporary directory '$1"',
	'errFtpDownloadFile' : 'Unable to download file "$1" ' from FTP,
	'errFtpUploadFile' : 'Cannot upload file "$1" to FTP',
	'errFtpMkdir' : 'Unable to create remote directory "$1"' on FTP,
	'errArchiveExec' : 'An error occurred while 'archive file' $1".
	'errExtractExec' : 'Error when extracting file $1".',
	'errNetUnMount' : 'Unable to uninstall.', // from v2.1 added 30.04.2012
	'errConvUTF8' : 'Not converted to UTF-8', // from v2.1 added 08.04.2014
	'errFolderUpload' : 'If you need to upload a directory, try using Google Chrome.', // from v2.1 added 26.6.2015
	'errSearchTimeout' : 'Search '$1" timeout, only partial search results are displayed. ', // from v2.1 added 12.1.2016
	'errReauthRequire' : 'Re-authorization required.', // from v2.1.10 added 24.3.2016
	'errMaxTargets' : 'The maximum number of selectable items is $1.', // from v2.1.17 added 17.10.2016
	'errRestore' : 'Unable to recover from the recycle bin, unable to identify the restore destination.', // from v2.1.24 added 3.5.2017
	'errEditorNotFound' : 'Can't find editor for this file.', // from v2.1.25 added 23.5.2017
	'errServerError' : 'An error occurred on the server.', // from v2.1.25 added 16.6.2017
	'errEmpty' : 'Cannot empty folder '$1".', // from v2.1.25 added 22.6.2017
	/******************************* commands names **************** ****************/
	'cmdarchive' : 'Create a tarball',
	'cmdback' : 'Back',
	'cmdcopy' : 'copy',
	'cmdcut' : 'Cut',
	'cmddownload' : 'Download',
	'cmdduplicate' : 'Create a copy',
	'cmdedit' : 'Edit file',
	'cmdextract' : 'Extract files from tarball',
	'cmdforward' : 'Forward',
	'cmdgetfile' : 'Select file',
	'cmdhelp' : 'About',
	'cmdhome' : 'Home',
	'cmdinfo' : 'View details',
	'cmdmkdir' : 'New folder',
	'cmdmkdirin' : 'to new folder', // from v2.1.7 added 19.2.2016
	'cmdmkfile' : 'New text file',
	'cmdopen' : 'Open',
	'cmdpaste' : 'Paste',
	'cmdquicklook' : 'Preview',
	'cmdreload' : 'refresh',
	'cmdrename' : 'rename',
	'cmdrm' : 'delete',
	'cmdtrash' : 'to recycle bin', //from v2.1.24 added 29.4.2017
	'cmdrestore' : 'restore', //from v2.1.24 added 3.5.2017
	'cmdsearch' : 'Find files',
	'cmdup' : 'Go to the previous folder',
	'cmdupload' : 'Upload file',
	'cmdview' : 'View',
	'cmdresize' : 'Resize & Rotate',
	'cmdsort' : 'sort',
	'cmdnetmount' : 'Load network volume', // added 18.04.2012
	'cmdnetunmount': 'Uninstall', // from v2.1 added 30.04.2012
	'cmdplaces' : 'Add to Favorites', // added 28.12.2014
	'cmdchmod' : 'change mode', // from v2.1 added 20.6.2015
	'cmdopendir' : 'open folder', // from v2.1 added 13.1.2016
	'cmdcolwidth' : 'Set column width', // from v2.1.13 added 12.06.2016
	'cmdfullscreen': 'full screen display', // from v2.1.15 added 03.08.2016
	'cmdmove' : 'move', // from v2.1.15 added 21.08.2016
	'cmdempty' : 'Empty folder', // from v2.1.25 added 22.06.2017
	'cmdundo' : 'Undo', // from v2.1.27 added 31.07.2017
	'cmdredo' : 'Redo', // from v2.1.27 added 31.07.2017
	'cmdpreference': 'Preference', // from v2.1.27 added 03.08.2017
	'cmdselectall' : 'Select all', // from v2.1.28 added 15.08.2017
	'cmdselectnone': 'All not selected', // from v2.1.28 added 15.08.2017
	'cmdselectinvert': 'reverse selection', // from v2.1.28 added 15.08.2017
	/*********************************** buttons ************* **********************/
	'btnClose' : 'close',
	'btnSave' : 'Save',
	'btnRm' : 'delete',
	'btnApply' : 'Apply',
	'btnCancel' : 'Cancel',
	'btnNo' : 'No',
	'btnYes' : 'Yes',
	'btnMount' : 'Load', // added 18.04.2012
	'btnApprove': 'to $1 and confirm', // from v2.1 added 26.04.2012
	'btnUnmount': 'Uninstall', // from v2.1 added 30.04.2012
	'btnConv' : 'convert', // from v2.1 added 08.04.2014
	'btnCwd' : 'here', // from v2.1 added 22.5.2015
	'btnVolume' : 'volume', // from v2.1 added 22.5.2015
	'btnAll' : 'All', // from v2.1 added 22.5.2015
	'btnMime' : 'MIME type', // from v2.1 added 22.5.2015
	'btnFileName': 'filename', // from v2.1 added 22.5.2015
	'btnSaveClose': 'Save and close', // from v2.1 added 12.6.2015
	'btnBackup' : 'backup', // fromv2.1 added 28.11.2015
	'btnRename' : 'rename', // from v2.1.24 added 6.4.2017
	'btnRenameAll' : 'Rename (All)', // from v2.1.24 added 6.4.2017
	'btnPrevious' : 'Prev ($1/$2)', // from v2.1.24 added 11.5.2017
	'btnNext' : 'Next ($1/$2)', // from v2.1.24 added 11.5.2017
	'btnSaveAs' : 'Save as', // from v2.1.25 added 24.5.2017
	/******************************** notifications **************** ****************/
	'ntfopen' : 'Open folder',
	'ntffile' : 'Open file',
	'ntfreload' : 'Refresh folder contents',
	'ntfmkdir' : 'Create folder',
	'ntfmkfile' : 'Create file',
	'ntfrm' : 'delete file',
	'ntfcopy' : 'copy file',
	'ntfmove' : 'move files',
	'ntfprepare' : 'Ready to copy files',
	'ntfrename' : 'Rename file',
	'ntfupload' : 'Upload file',
	'ntfdownload' : 'Download file',
	'ntfsave' : 'Save file',
	'ntfarchive' : 'Create a tarball',
	'ntfextract' : 'Extract files from tarball',
	'ntfsearch' : 'Search for files',
	'ntfresize' : 'changing size',
	'ntfsmth' : 'Busy>_<',
	'ntfloadimg' : 'Loading image',
	'ntfnetmount' : 'Loading network volume', // added 18.04.2012
	'ntfnetunmount': 'Uninstall network volume', // from v2.1 added 30.04.2012
	'ntfdim' : 'Get image size', // added 20.05.2013
	'ntfreaddir' : 'Reading folder information', // from v2.1 added 01.07.2013
	'ntfurl' : 'Getting the link address', // from v2.1 added 11.03.2014
	'ntfchmod' : 'changing file mode', // from v2.1 added 20.6.2015
	'ntfpreupload': 'Verifying upload file name', // from v2.1 added 31.11.2015
	'ntfzipdl' : 'Creating a download file', // from v2.1.7 added 23.1.2016
	'ntfparents' : 'Get path information', // from v2.1.17 added 2.11.2016
	'ntfchunkmerge': 'Processing upload file', // from v2.1.17 added 2.11.2016
	'ntftrash' : 'Move to Recycle Bin', // from v2.1.24 added 2.5.2017
	'ntfrestore' : 'Recover from recycle bin', // from v2.1.24 added 3.5.2017
	'ntfchkdir' : 'Check target folder', // from v2.1.24 added 3.5.2017
	'ntfundo' : 'Undo the last global operation', // from v2.1.27 added 31.07.2017
	'ntfredo' : 'Redo last global operation', // from v2.1.27 added 31.07.2017

	/*********************************** volumes ************* ********************/
	'volume_Trash' : 'Recycle Bin', //from v2.1.24 added 29.4.2017
	/************************************ dates ************ **********************/
	'dateUnknown' : 'unknown',
	'Today' : 'Today',
	'Yesterday' : 'Yesterday',
	'msJan' : 'January',
	'msFeb' : 'February',
	'msMar' : 'March',
	'msApr' : 'April',
	'msMay' : 'May',
	'msJun' : 'June',
	'msJul' : 'July',
	'msAug' : 'August',
	'msSep' : 'September',
	'msOct' : 'October',
	'msNov' : 'November',
	'msDec' : 'December',
	'January' : 'January',
	'February' : 'February',
	'March' : 'March',
	'April' : 'April',
	'May' : 'May',
	'June' : 'June',
	'July' : 'July',
	'August' : 'August',
	'September' : 'September',
	'October' : 'October',
	'November' : 'November',
	'December' : 'December',
	'Sunday' : 'Sunday',
	'Monday' : 'Monday',
	'Tuesday' : 'Tuesday',
	'Wednesday' : 'Wednesday',
	'Thursday' : 'Thursday',
	'Friday' : 'Friday',
	'Saturday' : 'Saturday',
	'Sun': 'Sunday',
	'Mon' : 'Monday',
	'Tue' : 'Tuesday',
	'Wed' : 'Wednesday',
	'Thu' : 'Thursday',
	'Fri' : 'Friday',
	'Sat' : 'Saturday',

	/******************************** sort variants *************** *****************/
	'sortname' : 'by name',
	'sortkind' : 'by type',
	'sortsize' : 'by size',
	'sortdate' : 'by date',
	'sortFoldersFirst' : 'folder first',
	'sortperm' : 'Sort by permission', // from v2.1.13 added 13.06.2016
	'sortmode' : 'Sort by attribute', // from v2.1.13 added 13.06.2016
	'sortowner' : 'Sort by owner', // from v2.1.13 added 13.06.2016
	'sortgroup' : 'sort by group', // from v2.1.13 added 13.06.2016
	'sortAlsoTreeview' : 'At the same time refresh the tree directory', // from v2.1.15 added 01.08.2016
	/********************************** new items ************* *********************/
	'untitled file.txt' : 'new file.txt', // added 10.11.2015
	'untitled folder' : 'new folder', // added 10.11.2015
	'Archive' : 'new zip package', // from v2.1 added 10.11.2015

	/********************************** messages ************** ********************/
	'confirmReq' : 'Please confirm',
	'confirmRm' : 'Are you sure you want to delete the file?<br/>The operation is irrevocable!',
	'confirmRepl' : 'Replace the original file with a new one?',
	'confirmRest' : 'Replace current item from recycle bin?', // fromv2.1.24 added 5.5.2017
	'confirmConvUTF8' : 'The file is not in UTF-8 format. Is it converted to UTF-8? By saving after conversion, the content becomes UTF-8.', // from v2.1 added 08.04.2014
	'confirmNonUTF8' : 'Unable to detect the character encoding of this file. You need to temporarily convert this file to UTF-8 encoding for editing.<br/>Please select the character encoding of this file.', // from v2.1.19 added 28.11 .2016
	'confirmNotSave' : 'The file has been edited.<br/> If you do not save directly, the edit will be lost.', // from v2.1 added 15.7.2015
	'confirmTrash' : 'Are you sure you want to move the item to the recycle bin?', //from v2.1.24 added 29.4.2017
	'apllyAll' : 'All apps',
	'name' : 'name',
	'size' : 'size',
	'perms' : 'permission',
	'modify' : 'Modify on',
	'kind' : 'category',
	'read' : 'read',
	'write' : 'write',
	'noaccess' : 'no permission',
	'and' : 'and',
	'unknown' : 'unknown',
	'selectall' : 'Select all files',
	'selectfiles' : 'Select file',
	'selectffile' : 'Choose the first file',
	'selectlfile' : 'Select last file',
	'viewlist' : 'list view',
	'viewicons' : 'icon view',
	'places' : 'location',
	'calc' : 'calculation',
	'path' : 'path',
	'aliasfor' : 'alias',
	'locked' : 'lock',
	'dim' : 'size',
	'files' : 'file',
	'folders' : 'folder',
	'items' : 'project',
	'yes' : 'Yes',
	'no' : 'No',
	'link' : 'link',
	'searcresult' : 'Search results',
	'selected' : 'Selected item',
	'about' : 'About',
	'shortcuts' : 'shortcut',
	'help' : 'Help',
	'webfm' : 'Network File Manager',
	'ver' : 'version',
	'protocolver' : 'protocol version',
	'homepage' : 'project home page',
	'docs' : 'document',
	'github' : 'Replicate our github',
	'twitter' : 'Follow our twitter',
	'facebook' : 'Join our facebook',
	'team' : 'team',
	'chiefdev' : 'Chief development',
	'developer' : 'development',
	'contributor' : 'contribute',
	'maintainer' : 'maintenance',
	'translator' : 'translation',
	'icons' : 'icon',
	'dontforget' : 'Don't forget to bring your towel to sweat',
	'shortcutsof' : 'The shortcut key is disabled',
	'dropFiles' : 'Drop files here',
	'or' : 'or',
	'selectForUpload' : 'Select the file to upload',
	'moveFiles' : 'move files',
	'copyFiles' : 'copy files',
	'restoreFiles' : 'Restore files', // from v2.1.24 added 5.5.2017
	'rmFromPlaces' : 'Remove from here',
	'aspectRatio' : 'Keep the ratio',
	'scale' : 'scaling ratio',
	'width' : 'wide',
	'height' : 'high',
	'resize' : 'Resize',
	'crop' : 'Cut',
	'rotate' : 'rotate',
	'rotate-cw' : 'Rotate 90° clockwise',
	'rotate-ccw' : 'Rotate 90° counterclockwise',
	'degree' : '°',
	'netMountDialogTitle' : 'Load network directory', // added 18.04.2012
	'protocol' : 'protocol', // added 18.04.2012
	'host' : 'host', // added 18.04.2012
	'port' : 'port', // added 18.04.2012
	'user' : 'user', // added 18.04.2012
	'pass' : 'password', // added 18.04.2012
	'confirmUnmount' : 'Do you want to uninstall $1?', // from v2.1 added 30.04.2012
	'dropFilesBrowser': 'Drag and drop or paste files from the browser', // from v2.1 added 30.05.2012
	'dropPasteFiles' : 'Drag and drop files, paste URLs or clipboard images', // from v2.1 added 07.04.2014
	'encoding' : 'encoding', // from v2.1 added 19.12.2014
	'locale' : 'locale', // from v2.1 added 19.12.2014
	'searchTarget' : 'Target: $1', // from v2.1 added 22.5.2015
	'searchMime' : 'Search by input MIME type', // from v2.1 added 22.5.2015
	'owner' : 'owner', // from v2.1 added 20.6.2015
	'group' : 'group', // from v2.1 added 20.6.2015
	'other' : 'other', // from v2.1 added 20.6.2015
	'execute' : 'execute', // from v2.1 added 20.6.2015
	'perm' : 'License', // from v2.1 added 20.6.2015
	'mode' : 'property', // from v2.1 added 20.6.2015
	'emptyFolder' : 'The folder is empty', // from v2.1.6 added 30.12.2015
	'emptyFolderDrop' : 'The folder is empty \\A drag and drop can append items', // from v2.1.6 added 30.12.2015
	'emptyFolderLTap' : 'The folder is empty \\A long press to add item', // from v2.1.6 added 30.12.2015
	'quality' : 'quality', // from v2.1.6 added 5.1.2016
	'autoSync' : 'automatic sync', // from v2.1.6 added 10.1.2016
	'moveUp' : 'move up', // from v2.1.6 added 18.1.2016
	'getLink' : 'Get URL link', // from v2.1.7 added 9.2.2016
	'selectedItems' : 'Selected item ($1)', // from v2.1.7 added 2.19.2016
	'folderId' : 'Directory ID', // from v2.1.10 added 3.25.2016
	'offlineAccess' : 'Allow offline operation', // from v2.1.10 added 3.25.2016
	'reAuth' : 're-verify', // from v2.1.10 added 3.25.2016
	'nowLoading' : 'Loading...', // from v2.1.12 added 4.26.2016
	'openMulti' : 'Open multiple files', // from v2.1.12 added 5.14.2016
	'openMultiConfirm': 'You are trying to open the $1 file. Are you sure you want to open it in your browser?', // from v2.1.12 added 5.14.2016
	'emptySearch' : 'There is no matching result in the search target', // from v2.1.12 added 5.16.2016
	'editingFile' : 'Editing file.', // from v2.1.13 added 6.3.2016
	'hasSelected' : 'Selected $1 items.', // from v2.1.13 added 6.3.2016
	'hasClipboard' : 'There are $1 items in the clipboard.', // from v2.1.13 added 6.3.2016
	'incSearchOnly' : 'Incremental search only from the current view.', // from v2.1.13 added 6.30.2016
	'reinstate' : 'restore', // from v2.1.15 added 3.8.2016
	'complete' : '$1 complete', // from v2.1.15 added 21.8.2016
	'contextmenu' : 'context menu', // from v2.1.15 added 9.9.2016
	'pageTurning' : 'Flip page', // from v2.1.15 added 10.9.2016
	'volumeRoots' : 'root directory', // from v2.1.16 added 16.9.2016
	'reset' : 'reset', // from v2.1.16 added 1.10.2016
	'bgcolor' : 'background color', // from v2.1.16 added 1.10.2016
	'colorPicker' : 'color selector', // from v2.1.16 added 1.10.2016
	'8pxgrid' : 'step size (8px)', // from v2.1.16 added 4.10.2016
	'enabled' : 'Enable', // from v2.1.16 added 4.10.2016
	'disabled' : 'off', // from v2.1.16 added 4.10.2016
	'emptyIncSearch' : 'No matching result in current view', // from v2.1.16 added 5.10.2016
	'emptyLetSearch' : 'The first letter search result in the current view is empty', // from v2.1.23 added 24.3.2017
	'textLabel' : 'text label', // from v2.1.17 added 13.10.2016
	'minsLeft' : 'Remaining $1 minute', // from v2.1.17 added 13.11.2016
	'openAsEncoding' : 'Reopen with selected encoding', // from v2.1.19 added 2.12.2016
	'saveAsEncoding' : 'Save with selected code', // from v2.1.19 added 2.12.2016
	'selectFolder' : 'Select directory', // from v2.1.20 added 13.12.2016
	'firstLetterSearch': 'first letter search', // from v2.1.23 added 24.3.2017
	'presets' : 'preset', // from v2.1.25 added 26.5.2017
	'tooManyToTrash' : 'There are too many items to move to the recycle bin.', // from v2.1.25 added 9.6.2017
	'TextArea' : 'text area', // from v2.1.25 added 14.6.2017
	'folderToEmpty' : 'Empty folder' $1".', // from v2.1.25 added 22.6.2017
	'filderIsEmpty' : 'Folder $1" is empty.', // from v2.1.25 added 22.6.2017
	'preference' : 'Preference', // from v2.1.26 added 28.6.2017
	'language' : 'language setting', // from v2.1.26 added 28.6.2017
	'clearBrowserData': 'Clear preferences saved in this browser', // from v2.1.26 added 28.6.2017
	'toolbarPref' : 'Toolbar settings', // from v2.1.27 added 2.8.2017
	'charsLeft' : '... $1 chars left.', // from v2.1.29 added 30.8.2017
	'sum' : 'total', // from v2.1.29 added 28.9.2017
	'roughFileSize' : 'rough file size', // from v2.1.30 added 2.11.2017
	'autoFocusDialog' : 'Automatically get focus when hovering over editable area in dialog box', // from v2.1.30 added 2.11.2017
	'select' : 'Select', // from v2.1.30 added 23.11.2017
	'selectAction' : 'When double-clicking on the selected file', // from v2.1.30 added 23.11.2017
	'useStoredEditor' : 'Open with the last used editor', // from v2.1.30 added 23.11.2017
	'selectinvert' : 'reverse selection', // from v2.1.30 added 25.11.2017
	'renameMultiple' : 'Are you sure you want to rename the selected item $1 to $2?<br/>This operation cannot be undone!', // from v2.1.31 added 4.12.2017
	'batchRename' : 'Batch rename', // from v2.1.31 added 8.12.2017
	'plusNumber' : 'increase quantity', // from v2.1.31 added 8.12.2017
	'asPrefix' : 'Add prefix', // from v2.1.31 added 8.12.2017
	'asSuffix' : 'Add suffix', // from v2.1.31 added 8.12.2017
	'changeExtention' : 'variation range', // from v2.1.31 added 8.12.2017

	/********************************** mimetypes ************** ********************/
	'kindUnknown' : 'unknown',
	'kindRoot' : 'root directory', // from v2.1.16 added 16.10.2016
	'kindFolder' : 'folder',
	'kindSelects' : 'select', // from v2.1.29 added 29.8.2017
	'kindAlias' : 'alias',
	'kindAliasBroken' : 'wrong alias',
	// applications
	'kindApp' : 'program',
	'kindPostscript' : 'Postscript document',
	'kindMsOffice' : 'Microsoft Office Document',
	'kindMsWord' : 'Microsoft Word Document',
	'kindMsExcel' : 'Microsoft Excel Document',
	'kindMsPP' : 'Microsoft Powerpoint Demo',
	'kindOO' : 'Open Office Document',
	'kindAppFlash' : 'Flash program',
	'kindPDF' : 'PDF document',
	'kindTorrent' : 'Bittorrent file',
	'kind7z' : '7z tarball',
	'kindTAR' : 'TAR tarball',
	'kindGZIP' : 'GZIP tarball',
	'kindBZIP' : 'BZIP tarball',
	'kindXZ' : 'XZ tarball',
	'kindZIP' : 'ZIP tarball',
	'kindRAR' : 'RAR tarball',
	'kindJAR' : 'Java JAR file',
	'kindTTF' : 'True Type font',
	'kindOTF' : 'Open Type font',
	'kindRPM' : 'RPM package',
// texts
'kindText' : 'text file',
'kindTextPlain' : 'plain text',
'kindPHP' : 'PHP source code',
'kindCSS' : 'Cascading Style Sheets (CSS)',
'kindHTML' : 'HTML document',
'kindJS' : 'Javascript source code',
'kindRTF' : 'rich text format (RTF)',
'kindC' : 'C source code',
'kindCHeader' : 'C header file',
'kindCPP' : 'C++ source code',
'kindCPPHeader' : 'C++ header file',
'kindShell' : 'Unix shell script',
'kindPython' : 'Python source code',
'kindJava' : 'Java source code',
'kindRuby' : 'Ruby source code',
'kindPerl' : 'Perl source code',
'kindSQL' : 'SQL script',
'kindXML' : 'XML document',
'kindAWK' : 'AWK source code',
'kindCSV' : 'Comma Separated Value File (CSV)',
'kindDOCBOOK' : 'Docbook XML Document',
'kindMarkdown' : 'Markdown text', // added 20.7.2015
// images
'kindImage' : 'picture',
'kindBMP' : 'BMP picture',
'kindJPEG' : 'JPEG image',
'kindGIF' : 'GIF picture',
'kindPNG' : 'PNG picture',
'kindTIFF' : 'TIFF picture',
'kindTGA' : 'TGA picture',
'kindPSD' : 'Adobe Photoshop picture',
'kindXBITMAP' : 'X bitmap picture',
'kindPXM' : 'Pixelmator picture',
// media
'kindAudio' : 'audio',
'kindAudioMPEG' : 'MPEG Audio',
'kindAudioMPEG4' : 'MPEG-4 Audio',
'kindAudioMIDI' : 'MIDI audio',
'kindAudioOGG' : 'Ogg Vorbis Audio',
'kindAudioWAV' : 'WAV Audio',
'AudioPlaylist' : 'MP3 playlist',
'kindVideo' : 'video',
'kindVideoDV' : 'DV video',
'kindVideoMPEG' : 'MPEG Video',
'kindVideoMPEG4' : 'MPEG-4 Video',
'kindVideoAVI' : 'AVI video',
'kindVideoMOV' : 'Quick Time Video',
'kindVideoWM' : 'Windows Media Video',
'kindVideoFlash' : 'Flash Video',
'kindVideoMKV' : 'Matroska video',
'kindVideoOGG' : 'Ogg video'
}
};
}));